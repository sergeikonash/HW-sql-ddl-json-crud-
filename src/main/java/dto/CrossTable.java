package dto;

public class CrossTable {
    private long studentId;
    private long groupId;

    public CrossTable(long studentId, long groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
