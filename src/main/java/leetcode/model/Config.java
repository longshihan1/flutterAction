package leetcode.model;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private Integer version;
    private String id;
    private String name;
    private String loginName;
    private String password;
    private String filePath;
    private String codeType;
    private String url;
    private List<String> favoriteList;

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLoginName()
    {
        return this.loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFilePath()
    {
        return this.filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getCodeType()
    {
        return this.codeType;
    }

    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }


    public Integer getVersion()
    {
        return this.version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    public List<String> getFavoriteList()
    {
        if ((this.favoriteList == null) || (this.favoriteList.isEmpty()))
        {
            this.favoriteList = new ArrayList();
            this.favoriteList.add("Favorite");
        }
        return this.favoriteList;
    }

    public void setFavoriteList(List<String> favoriteList)
    {
        this.favoriteList = favoriteList;
    }

    public String getAlias()
    {
        if ("leetcode.com".equals(getUrl())) {
            return "en";
        }
        return "cn";
    }

}
