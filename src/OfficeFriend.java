public class OfficeFriend extends OfficialRecipient implements BirthdayWishable{
    private String birthday;
    OfficeFriend(String name,String mailAddress,String designation,String birthday){
        super(name,mailAddress,designation);
        this.birthday = birthday;
    }

    @Override
    public String showDetail() {
        return "Office_friend: "+getName()+","+getMailAddress()+","+getDesignation()+","+birthday;
    }

    public String getBirthday(){
        return birthday;
    }
}
