package vcs;

import filesystem.FileSystemOperation;
import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class StatusOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {

        vcs.getOutputWriter().write("On branch: " + vcs.getCurrentBranch().getName() + '\n');
        vcs.getOutputWriter().write("Staged changes:" + '\n');
        for (FileSystemOperation op : vcs.getStaging().getStagingQueue()) {
            switch (op.getType()) {
                case CHANGEDIR:
                    vcs.getOutputWriter().write('\t' + "Chaged directory "
                            + vcs.getActiveSnapshot().getCurrentDir().getName() + " to "
                            + op.getOperationArgs().get(op.getOperationArgs().size() - 1) + '\n');
                    break;

                case MAKEDIR:
                    vcs.getOutputWriter().write('\t' + "Created directory "
                            + op.getOperationArgs().get(op.getOperationArgs().size() - 1) + '\n');
                    break;

                case REMOVE:
                    vcs.getOutputWriter().write('\t' + "Removed "
                            + op.getOperationArgs().get(op.getOperationArgs().size() - 1) + '\n');
                    break;

                case TOUCH:
                    vcs.getOutputWriter().write('\t' + "Created file "
                            + op.getOperationArgs().get(op.getOperationArgs().size() - 1) + '\n');
                    break;

                case WRITETOFILE:
                    vcs.getOutputWriter().write('\t' + "Added \""
                            + op.getOperationArgs().get(op.getOperationArgs().size() - 2)
                    + "\" to file " + op.getOperationArgs().get(op.getOperationArgs().size() - 1)
                    + '\n');
                    break;
                    default:
                        break;
            }
        }
        return ErrorCodeManager.OK;
    }
}
