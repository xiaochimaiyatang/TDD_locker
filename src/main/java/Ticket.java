public class Ticket {
    private String boxId;
    private Boolean isValid;

    public Ticket(String id) {
        isValid = true;
        boxId = id;
    }

    public String getBoxId() {
        return boxId;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setInvalid() {
        isValid = false;
    }
}
