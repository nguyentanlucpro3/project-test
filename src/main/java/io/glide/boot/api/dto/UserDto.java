package io.glide.boot.api.dto;

public class UserDto {

    /**
     * User id
     */
    private long id;

    private UserInfos userInfos;

    public UserDto() {
    }

    public UserDto(final long id, final UserInfos userInfos) {
        this.id = id;
        this.userInfos = userInfos;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public UserInfos getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(final UserInfos userInfos) {
        this.userInfos = userInfos;
    }


}
