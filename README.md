# Selenium
Описание решения:

Для разработки выбран следующий стек технологий: java, maven, selenium Chrome WebDriver.

Для теста подготовлено два PageObject:
1) GoogleSearchPage
1.1. Страница поиска гугл www.google.com
1.2. Метод вставки данных в строку поиска
1.3. Метод запуска поиска.
2) GoogleSearchResultPage
2.1. Страница с результатом поиска. В рамках этой страницы реализован лишь метод изъятия нужного контейнера. Так как более глубокие методы в рамках данного теста не требуются.

Для запуска функционального теста с другими типами браузеров необходимо переделать тест. А именно:
@Before
public void init(){
    this.driver = new ChromeDriver();
    logger.debug("ChromeDriver is running");
    this.driver.manage().window().fullscreen();
    logger.debug("set fullsize of window");

Необходимо заменить this.driver = ChromeDriver() на любой из типов драйверов, которые будут установлены в системе. Текущий конфиг теста, производит запуск функционального теста в браузере Chrome.

Для запуска теста в Windows машине необходимо установить в ОС сборщик maven и java vm.Прописать его в переменные окружения ОС. Далее проверить что он вызывается в cmd. Для этого откройте консоль и введите: mvn -version. Если переменные окружения прописаны верно, то вы увидите ответ о версии maven.

После того как сборщик будет установлен в ОС, пройдите в папку с проектом и запустите в консоли команду: mvn -Dtest=tests.Selenium test. После этого начнется сборка проекта и запуск теста. 

Альтернативным способом запуска является запуск теста через IDE.

В текущей версии теста присутствует модуль сборки артефактов прогона:

1. Отдельное логирование теста в файл log4j-application.log.
2. В случае если конечная точка теста (url после перехода по первому результату поиска) не является yandex.ru, производится сохранение скриншота текущего окна и запись в лог результата Assertion, а также сохранение текущего полученного html документа из окна прогона теста. Это поможет в разборе результата прогона.

ВАЖНО! Сейчас в код теста намеренно внесена ошибка:

assertTwoStringParams("just_test_of_artefacts_Grubber", current_url, driver, this.testName + "_failed_assert_current_url", logger);

Это сделано для того, чтобы продемонстрировать механизм сборки артефактов прогона.

Чтобы тест корректно проходил необходимо заменить эту строку на строку:
assertTwoStringParams("https://yandex.ru/", current_url, driver, this.testName + "_failed_assert_current_url", logger);

