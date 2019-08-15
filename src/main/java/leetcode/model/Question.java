package leetcode.model;

import org.apache.commons.lang.StringUtils;

public class Question {
    private String title;
    private String questionId;
    private String questionTypename;
    private String typeName;
    private Integer level;
    private String status;
    private String titleSlug;
    private boolean leaf = Boolean.FALSE.booleanValue();
    private String testCase;
    private String langSlug;
    private String nodeType = "def";
    private String frontendQuestionId;

    public Question() {}

    public Question(String title)
    {
        this.title = title;
    }

    public Question(String title, String nodeType)
    {
        this.title = title;
        this.nodeType = nodeType;
    }

    public String getTitle()
    {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(this.frontendQuestionId)) {
            sb.append("[").append(this.frontendQuestionId).append("]");
        }
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getQuestionId()
    {
        return this.questionId;
    }

    public void setQuestionId(String questionId)
    {
        this.questionId = questionId;
    }

    public String getQuestionTypename()
    {
        return this.questionTypename;
    }

    public void setQuestionTypename(String questionTypename)
    {
        this.questionTypename = questionTypename;
    }

    public String getTypeName()
    {
        return this.typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public Integer getLevel()
    {
        return this.level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean isLeaf()
    {
        return this.leaf;
    }

    public void setLeaf(boolean leaf)
    {
        this.leaf = leaf;
    }

    public String getTitleSlug()
    {
        return this.titleSlug;
    }

    public void setTitleSlug(String titleSlug)
    {
        this.titleSlug = titleSlug;
    }

    public String getTestCase()
    {
        return this.testCase;
    }

    public void setTestCase(String testCase)
    {
        this.testCase = testCase;
    }

    public String getLangSlug()
    {
        return this.langSlug;
    }

    public void setLangSlug(String langSlug)
    {
        this.langSlug = langSlug;
    }

    public String getNodeType()
    {
        return this.nodeType;
    }

    public void setNodeType(String nodeType)
    {
        this.nodeType = nodeType;
    }

    public String getFrontendQuestionId()
    {
        return this.frontendQuestionId;
    }

    public void setFrontendQuestionId(String frontendQuestionId)
    {
        this.frontendQuestionId = frontendQuestionId;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        if ("notac".equals(this.status)) {
            sb.append("❓");
        } else if ("ac".equals(this.status)) {
            sb.append("✔");
        } else if ("lock".equals(this.status)) {
            sb.append(" $ ");
        } else if ((this.leaf) && (this.level != null)) {
            sb.append("   ");
        }
        if ((StringUtils.isNotBlank(this.frontendQuestionId)) && (this.leaf)) {
            sb.append("[").append(this.frontendQuestionId).append("]");
        }
        return this.title;
    }
}
