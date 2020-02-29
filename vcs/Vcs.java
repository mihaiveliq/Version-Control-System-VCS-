package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;

import java.util.ArrayList;
import java.util.Iterator;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private ArrayList<Branch> branchList;
    private Staging staging;

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        this.branchList = new ArrayList<>();
        this.staging = new Staging();
        Branch firstBranch = new Branch("master");
        Commit firstCommit = new Commit(new FileSystemSnapshot(outputWriter),
                "First commit");
        firstBranch.getCommitList().add(firstCommit);
        this.branchList.add(firstBranch);
        setCurrentBranch("master");
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        return vcsOperation.execute(this);
    }
    /**
     * Create an internal class for staging.
     *
     */
    public final class Staging {
        private ArrayList<FileSystemOperation> stagingQueue;
        Staging() {
            stagingQueue = new ArrayList<>();
        }

        public void addStaging(FileSystemOperation fileSystemOperation) {
            stagingQueue.add(fileSystemOperation);
        }

        ArrayList<FileSystemOperation> getStagingQueue() {
            return this.stagingQueue;
        }
    }
    /**
     * Returns the current Branch.
     *
     */
    Branch getCurrentBranch() {
        Iterator<Branch> it = branchList.iterator();

        while (it.hasNext()) {
            Branch temp = it.next();
            if (temp.getFlagCurrBranch()) {
                return temp;
            }
        }
        return branchList.get(branchList.size() - 1);
    }

    /**
     * Returns the head Commit.
     *
     */
    Commit getHead() {
        for (Branch temp : branchList) {
            for (Commit tmp : temp.getCommitList()) {
                if (tmp.getCommitHead()) {
                    return tmp;
                }
            }
        }
        return branchList.get(0).getCommitList().get(0);
    }

    /**
     * Sets the Branch with the name "name" as current Branch.
     *
     */
    void setCurrentBranch(String name) {
        Iterator<Branch> it = branchList.iterator();

        while (it.hasNext()) {
            Branch temp = it.next();
            if (temp.getName().equals(name)) {
                getHead().setCommitHead(false);
                temp.getCommitList().get(temp.getCommitList().size() - 1).setCommitHead(true);
                getCurrentBranch().setFlagCurrBranch(false);
                temp.setFlagCurrBranch(true);
            }
        }
    }

    /**
     * Ads the Branch "branch" to this branchList.
     *
     */
    void addBranchList(Branch branch) {
        branchList.add(branch);
    }

    /**
     * Checks if the current branchList contains the commit with the ID "id".
     *
     */
    boolean hasCommit(int id) {
        for (Branch temp : branchList) {
            for (Commit tmp : temp.getCommitList()) {
                if (tmp.getCommitId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This getters and setters.
     *
     */
    FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    public Staging getStaging() {
        return this.staging;
    }

    ArrayList<Branch> getBranchList() {
        return branchList;
    }

    public OutputWriter getOutputWriter() {
        return this.outputWriter;
    }

    public void setActiveSnapshot(FileSystemSnapshot systemSnapshot) {
        activeSnapshot = systemSnapshot;
    }
}
