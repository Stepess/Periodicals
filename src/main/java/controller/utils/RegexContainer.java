package controller.utils;

public interface RegexContainer {
    String TITLE_EN = "^[A-Za-z]+([A-Za-z]|\\s|,|\\.|\\-)*$";
    String TITLE_UA = "^[А-ЩЄІЇЮЯҐ]{1}[а-щєіїьюяґ'\\s|,|\\.|\\-]+$";
    String AUTHOR_EN = "^[A-Za-z]+([A-Za-z]|\\s|,|\\.|\\-)*$";
    String AUTHOR_UA = "^[А-ЩЄІЇЮЯҐ]{1}[а-щєіїьюяґ'\\s|,|\\.|\\-]+$";
    String GENRE_EN = "^[A-Za-z]+([A-Za-z]|\\s|,|\\.|\\-)*$";
    String GENRE_UA = "^[А-ЩЄІЇЮЯҐ]{1}[а-щєіїьюяґ'\\s|,|\\.|\\-]+$";
    String PRICE = "[0-9]{1,3}\\.?[0-9]{0,2}";
    String DESCRIPTION_EN = "^[A-Za-z]+([A-Za-z]|\\s|,|\\.|\\-)*$";
    String DESCRIPTION_UA = "^[А-ЩЄІЇЮЯҐ]{1}[а-щєіїьюяґ'\\s|,|\\.|\\-]+$";



    String LOGIN_REGEX = "^[A-Za-z0-9_-]{5,15}$";
    String FIRST_NAME_REGEX = "^[A-Z][a-z]+$";
    String EMAIL_REGEX = "^[A-Za-z0-9_-]{3,15}@[a-z]{3,10}\\.[a-z]{2,3}$";
}
