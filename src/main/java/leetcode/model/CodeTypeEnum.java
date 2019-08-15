package leetcode.model;

import java.util.HashMap;
import java.util.Map;

public enum CodeTypeEnum {
    JAVA("Java", "java", ".java", "//"),  PYTHON("Python", "python", ".py", "#"),  CPP("C++", "cpp", ".cpp", "//"),  PYTHON3("Python3", "python3", ".py", "#"),  C("C", "c", ".c", "//"),  CSHARP("C#", "csharp", ".cs", "//"),  JAVASCRIPT("JavaScript", "javascript", ".js", "//"),  RUBY("Ruby", "ruby", ".rb", "#"),  SWIFT("Swift", "swift", ".swift", "///"),  GO("Go", "golang", ".go", "//"),  SCALA("Scala", "scala", ".scala", "//"),  KOTLIN("Kotlin", "kotlin", ".kt", "//"),  RUST("Rust", "rust", ".rs", "//"),  PHP("PHP", "php", ".php", "//");

    private String type;
    private String langSlug;
    private String suffix;
    private String comment;
    private static Map<String, CodeTypeEnum> MAP;
    private static Map<String, CodeTypeEnum> LANGSLUGMAP;

    private CodeTypeEnum(String type, String langSlug, String suffix, String comment)
    {
        this.type = type;
        this.langSlug = langSlug;
        this.suffix = suffix;
        this.comment = comment;
    }

    static
    {
        MAP = new HashMap();
        LANGSLUGMAP = new HashMap();
        for (CodeTypeEnum c : values())
        {
            MAP.put(c.getType().toUpperCase(), c);
            LANGSLUGMAP.put(c.langSlug.toUpperCase(), c);
        }
    }

    public String getType() {
        return this.type;
    }

    public String getSuffix()
    {
        return this.suffix;
    }

    public static CodeTypeEnum getCodeTypeEnum(String type)
    {
        return (CodeTypeEnum)MAP.get(type.toUpperCase());
    }

    public static CodeTypeEnum getCodeTypeEnumByLangSlug(String langSlug)
    {
        return (CodeTypeEnum)LANGSLUGMAP.get(langSlug.toUpperCase());
    }

    public String getComment()
    {
        return this.comment;
    }
}
