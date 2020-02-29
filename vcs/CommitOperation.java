package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class CommitOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        if (vcs.getStaging().getStagingQueue().isEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        Commit newCommit = new Commit(vcs.getActiveSnapshot(),
                operationArgs.get(operationArgs.size() - 1));
        vcs.getStaging().getStagingQueue().clear();
        vcs.getCurrentBranch().getCommitList().add(newCommit);
        return ErrorCodeManager.OK;
    }
}
