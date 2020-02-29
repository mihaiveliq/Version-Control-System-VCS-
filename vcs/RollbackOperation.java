package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class RollbackOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {

        vcs.getStaging().getStagingQueue().clear();
        vcs.setActiveSnapshot(vcs.getCurrentBranch().getCommitList().
                        get(vcs.getCurrentBranch().getCommitList().size() - 1).getSnapshot());
        return ErrorCodeManager.OK;
    }
}
