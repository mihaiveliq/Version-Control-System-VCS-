package vcs;

import filesystem.FileSystemSnapshot;
import utils.IDGenerator;

public final class Commit {
    private FileSystemSnapshot snapshot;
    private int commitId;
    private String message;
    private boolean head;

    Commit(FileSystemSnapshot sn, String message) {
        snapshot = sn.cloneFileSystem();
        commitId = IDGenerator.generateCommitID();
        this.message = message;
        head = true;
    }

    FileSystemSnapshot getSnapshot() {
        return snapshot;
    }

    int getCommitId() {
        return commitId;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getCommitHead() {
        return this.head;
    }

    public void setCommitHead(boolean value) {
        this.head = value;
    }
}
