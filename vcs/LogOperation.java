package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class LogOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {

        Branch currBranch = vcs.getCurrentBranch();
        for (Commit temp : currBranch.getCommitList()) {
            if (temp.equals(currBranch.getCommitList().
                    get(currBranch.getCommitList().size() - 1))) {
                vcs.getOutputWriter().write("Commit id: " + temp.getCommitId() + '\n');
                if (temp.getMessage().startsWith(" ")) {
                    vcs.getOutputWriter().write("Message:" + temp.getMessage() + '\n');
                } else {
                    vcs.getOutputWriter().write("Message: " + temp.getMessage() + '\n');
                }
            } else {
                vcs.getOutputWriter().write("Commit id: " + temp.getCommitId() + '\n');
                if (temp.getMessage().startsWith(" ")) {
                    vcs.getOutputWriter().write("Message:" + temp.getMessage() + '\n' + '\n');
                } else {
                    vcs.getOutputWriter().write("Message: " + temp.getMessage() + '\n' + '\n');
                }
            }
        }
        return ErrorCodeManager.OK;
    }
}
