package annotation.basic;

@AnnoMeta
public class Metadata {

    //@AnnoMeta // 필드에 적용 - 컴파일 오류
    private String id;

    @AnnoMeta // 메서드에 적용
    public void call() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        AnnoMeta typeAnno = Metadata.class.getAnnotation(AnnoMeta.class);
        System.out.println("typeAnno = " + typeAnno);

        AnnoMeta methodAnno = Metadata.class.getMethod("call").getAnnotation(AnnoMeta.class);
        System.out.println("methodAnno = " + methodAnno);
    }

}
