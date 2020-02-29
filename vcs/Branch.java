package vcs;

import java.util.ArrayList;

public final class Branch {
    private String name;
    private ArrayList<Commit> commitList;
    private boolean flagCurrBranch;

    Branch(String string) {
        flagCurrBranch = false;
        name = string;
        commitList = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    ArrayList<Commit> getCommitList() {
        return commitList;
    }

    public boolean getFlagCurrBranch() {
        return this.flagCurrBranch;
    }

    public void setFlagCurrBranch(boolean value) {
        this.flagCurrBranch = value;
    }
}
