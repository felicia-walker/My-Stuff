package nihon;

public class KanjiFlash extends FlashBase
{

    private KanjiFlash(final String filename) throws Exception
    {
        super(filename);
    }

    public static void main(String[] args) throws Exception
    {
        String dir = System.getProperty("user.dir");
        //KanjiFlash kanjiflash = new KanjiFlash(dir + "\\src\\resources\\Kanji_n5.tsv");
        KanjiFlash kanjiflash = new KanjiFlash(dir + "\\src\\resources\\jlpt_vocab_n5.tsv");
        kanjiflash.iterateItems();
    }
}
