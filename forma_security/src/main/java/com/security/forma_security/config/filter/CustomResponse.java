package com.security.forma_security.config.filter;

import java.util.Objects;

public class CustomResponse {
    private String access_token;
    private String refresh_token;

    private UserDTO user;

    public CustomResponse(String access_token, String refresh_token, UserDTO user) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.user = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(access_token, refresh_token, user);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if ( obj == null || getClass() != obj.getClass()) return false;

        CustomResponse res = (CustomResponse) obj;
        return access_token == res.access_token && refresh_token == res.refresh_token && user == res.user;    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("access_token : '").append(access_token).append('\'');
        sb.append(", refresh_token : '").append(refresh_token).append('\'');
        sb.append(", user {'").append('\'');
        sb.append(", username :").append(user.getUsername() + "'").append('\'');
        sb.append(", roles : [");
                for(String role : user.getRoles()) {
                    sb.append(role + ", ") ;
                }
        sb.append(" ]");

        sb.append('}');
        return sb.toString();
    }


}
