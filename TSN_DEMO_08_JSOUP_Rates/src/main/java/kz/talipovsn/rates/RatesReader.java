package kz.talipovsn.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// СОЗДАТЕЛЬ КОТИРОВОК ВАЛЮТ
public class RatesReader {

    private static final String BASE_URL = "https://shop.casio.ru/catalog"; // Адрес с котировками

    // Парсинг котировок из формата html web-страницы банка, при ошибке доступа возвращаем null
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {
            data.append("Товары бренда Casio:\n");

            Document doc = Jsoup.connect(BASE_URL).timeout(5000).get(); // Создание документа JSOUP из html

            Elements brand = doc.select("img.product-item__brand-img");
            Elements articul = doc.select("p.product-item__articul");

            // Цикл по строкам таблицы
            for (int i = 0; i < brand.size(); i++) {
                String str_brand  = brand.get(i).attr("alt");
                String str_articul = articul.get(i).text();

                data.append((i + 1) + ") Brand : " +  str_brand  + "\n" +
                        "Model : " + str_articul + "\n\n");

            }
        } catch (Exception ignored) {
            return null; // При ошибке доступа возвращаем null
        }
        return data.toString().trim(); // Возвращаем результат
    }

}