public class OfficialRecipient extends Recipient{
    private String designation;
    OfficialRecipient(String name,String mailAddress,String designation){
        super(name,mailAddress);
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
    @Override
    public String showDetail() {
        return "Officail: "+getName()+","+getMailAddress()+","+designation;
    }
}
