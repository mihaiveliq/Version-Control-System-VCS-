package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;
import java.util.Iterator;

public final class CheckoutOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {

        if (!vcs.getStaging().getStagingQueue().isEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }
        if (operationArgs.size() > 1) {
            if (operationArgs.get(operationArgs.size() - 2).equals("-c")) {
                int auxcommID = Integer.parseInt(operationArgs.get(operationArgs.size() - 1));
                if (!vcs.hasCommit(auxcommID)) {
                    return ErrorCodeManager.VCS_BAD_PATH_CODE;
                } else {
                    vcs.getHead().setCommitHead(false);
                    Iterator<Branch> branchIterator = vcs.getBranchList().iterator();

                    while (branchIterator.hasNext()) {
                        Branch temp = branchIterator.next();

                        Iterator<Commit> commitIterator = temp.getCommitList().iterator();

                        while (commitIterator.hasNext()) {
                            Commit tmp = commitIterator.next();
                            if (tmp.getCommitId() == auxcommID) {
                                tmp.setCommitHead(true);
                                vcs.setActiveSnapshot(tmp.getSnapshot());
                            }
                            if (tmp.getCommitId() > auxcommID) {
                                commitIterator.remove();
                            }
                        }

                    }
                }
            }
        } else {
            boolean ok = false;
                for (Branch temp : vcs.getBranchList()) {
                    if (temp.getName().equals(operationArgs.get(operationArgs.size() - 1))) {
                        ok = true;
                    }
                }
                if (!ok) {
                    return ErrorCodeManager.VCS_BAD_CMD_CODE;
                }
                vcs.setCurrentBranch(operationArgs.get(operationArgs.size() - 1));

        }
        return ErrorCodeManager.OK;
    }
}
