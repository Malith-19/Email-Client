public class PersonalRecipient extends Recipient implements BirthdayWishable{
    private String nickName;
    private String birthday;
    PersonalRecipient(String name,String nickName,String mailAddress,String birthday){
        super(name,mailAddress);
        this.nickName = nickName;
        this.birthday = birthday;
    }


    @Override
    public String showDetail() {
        return "Personal: "+getName()+","+nickName+","+getMailAddress()+","+birthday;
    }

    @Override
    public String getBirthday() {
        return birthday;
    }
}