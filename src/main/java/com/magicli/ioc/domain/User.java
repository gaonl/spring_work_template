package com.magicli.ioc.domain;

/**
 * Created by gaonl on 2018/9/28.
 */
public class User {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    private static final String toStringJsonFmt = "{\"id\":%s,\"name\":\"%s\"}";

    @Override
    public String toString() {
        return String.format(toStringJsonFmt, String.valueOf(id), String.valueOf(name));
    }
}
