package com.example.penyok.yandextestproject.server;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Список допустимых языков для перевода.
 */
public class Languages {

    private static Map<String, String> languages = new HashMap<>();

    public Languages() {
        if (languages.size() == 0) {
            languages.put("Азербайджанский", "az");
            languages.put("Албанский", "sq");
            languages.put("Амхарский", "am");
            languages.put("Английский", "en");
            languages.put("Арабский", "ar");
            languages.put("Армянский", "hy");
            languages.put("Африкаанс", "af");
            languages.put("Баскский", "eu");
            languages.put("Башкирский", "ba");
            languages.put("Белорусский", "be");
            languages.put("Бенгальский", "bn");
            languages.put("Болгарский", "bg");
            languages.put("Боснийский", "bs");
            languages.put("Валлийский", "cy");
            languages.put("Венгерский", "hu");
            languages.put("Вьетнамский", "vi");
            languages.put("Гаитянский ", "ht");
            languages.put("Галисийский", "gl");
            languages.put("Голландский", "nl");
            languages.put("Горномарийский", "mrj");
            languages.put("Греческий", "el");
            languages.put("Грузинский", "ka");
            languages.put("Гуджарати", "gu");
            languages.put("Датский", "da");
            languages.put("Иврит", "he");
            languages.put("Идиш", "yi");
            languages.put("Индонезийский", "id");
            languages.put("Ирландский", "ga");
            languages.put("Итальянский", "it");
            languages.put("Исландский", "is");
            languages.put("Испанский", "es");
            languages.put("Казахский", "kk");
            languages.put("Каннада", "kn");
            languages.put("Каталанский", "ca");
            languages.put("Киргизский", "ky");
            languages.put("Китайский", "zh");
            languages.put("Корейский", "ko");
            languages.put("Коса", "xh");
            languages.put("Латынь", "la");
            languages.put("Латышский", "lv");
            languages.put("Литовский", "lt");
            languages.put("Лксембургский", "lb");
            languages.put("Малагасийский", "mg");
            languages.put("Малайский", "ms");
            languages.put("Малаялам", "ml");
            languages.put("Мальтийский ", "mt");
            languages.put("Македонский", "mk");
            languages.put("Маори", "mi");
            languages.put("Маратхи", "mr");
            languages.put("Марийский", "mhr");
            languages.put("Монгольский", "mn");
            languages.put("Немецкий", "de");
            languages.put("Непальский", "ne");
            languages.put("Норвежский", "no");
            languages.put("Панджаби", "pa");
            languages.put("Папьяменто", "pap");
            languages.put("Персидский", "fa");
            languages.put("Польский", "pl");
            languages.put("Португальский", "pt");
            languages.put("Румынский", "ro");
            languages.put("Русский", "ru");
            languages.put("Себуанский", "ceb");
            languages.put("Сербский", "sr");
            languages.put("Сингальский", "si");
            languages.put("Словацкий", "sk");
            languages.put("Словенский", "sl");
            languages.put("Суахили", "sw");
            languages.put("Сунданский", "su");
            languages.put("Таджикский", "tg");
            languages.put("Тайский", "th");
            languages.put("Тагальский", "tl");
            languages.put("Тамильский", "ta");
            languages.put("Татарский", "tt");
            languages.put("Телугу", "te");
            languages.put("Турецкий ", "tr");
            languages.put("Удмуртский", "udm");
            languages.put("Узбекский", "uz");
            languages.put("Украинский", "uk");
            languages.put("Урду", "ur");
            languages.put("Финский", "fi");
            languages.put("Французский", "fr");
            languages.put("Хинди", "hi");
            languages.put("Хорватский", "hr");
            languages.put("Чешский", "cs");
            languages.put("Шведский", "sv");
            languages.put("Шотландский", "gd");
            languages.put("Эстонский", "et");
            languages.put("Эсперанто", "eo");
            languages.put("Яванский", "jv");
            languages.put("Японский", "ja");
        }
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getLanguagesKeys() {
        return new ArrayList<>(languages.keySet());
    }

    public ArrayList<String> getLanguagesValues() {
        return new ArrayList<>(languages.values());
    }

}
