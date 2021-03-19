package com.example.s3.Complaint;

public class ComplaintItem {
    private int Image;
    private String mText1;
    private String mText2;
    private String mid;
    private String mHostelNo,mFloorNo,mRoomNo,mMessNo,mWashroomNo,mStaffName,mStaffRole,mDescr,mcategory,msubcategory,mpriority;

    public ComplaintItem(int imageResource, String text1, String text2,String category, String subcategory,String HostelNo,String FloorNo,String RoomNo,String MessNo,String WashroomNo,String StaffName,String StaffRole,String Descr,String priority,String id) {
        Image = imageResource;
        mText1 = text1;
        mText2 = text2;
        mid=id;
        mHostelNo=HostelNo;
        mFloorNo=FloorNo;
        mRoomNo=RoomNo;
        mMessNo=MessNo;
        mWashroomNo=WashroomNo;
        mStaffName=StaffName;
        mStaffRole=StaffRole;
        mDescr=Descr;
        mcategory=category;
        msubcategory=subcategory;
        mpriority=priority;
    }
    public int getImageResource() {
        return Image;
    }
    public String getText1() {
        return mText1;
    }
    public String getText2() {
        return mText2;
    }
    public String getText3() {
        return mcategory;
    }
    public String getText4() {
        return msubcategory;
    }

    public String getid() {
        return mid;
    }

    public void changeText1(String text) {
        mText1 = text;
    }
}
