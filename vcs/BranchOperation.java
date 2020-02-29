package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class BranchOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        for (Branch temp : vcs.getBranchList()) {
            if (operationArgs.get(operationArgs.size() - 1).equals(temp.getName())) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        //System.out.println(newCommit.getCommitId() + "    " + newCommit.head);
        //vcs.getHead().head = false;
        //System.out.println(newCommit.getCommitId() + "    " + newCommit.head);
        String brName = operationArgs.get(operationArgs.size() - 1);
        //vcs.getCurrentBranch().currBranch = false;
        Branch newBranch = new Branch(brName);
        newBranch.getCommitList().add(vcs.getHead());
        vcs.addBranchList(newBranch);
        //vcs.getBranchList().add(newBranch);
        //vcs.setCurrentBranch(brName);
        return ErrorCodeManager.OK;
    }
}
