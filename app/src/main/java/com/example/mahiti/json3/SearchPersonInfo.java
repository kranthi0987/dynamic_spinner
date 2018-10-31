package com.example.mahiti.json3;

public class SearchPersonInfo{
    private String hamlet;
    private String street;
    private String name;
    private String occupation;
    private String village;
    public void search(){
        String query;
        query="SELECT * from yaletable where village="+village;
        if(!hamlet.isEmpty()) {
            query = query + " AND hamlet like"+hamlet+"%";
        }
        if(!street.isEmpty()){
            query=query + " AND street like" + street + "%" ;

        }
        if(!name.isEmpty()){
            query=query+" AND given_name like " +name+"%";

        }
        if(!occupation.isEmpty()){
            query=query+" AND primary_occupation like"+occupation + "%" +" OR secondary_occupation like" +occupation + "%";
        }
    }
}
