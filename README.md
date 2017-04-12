Instalacja narzędzi do angulara2:

1. NVM (node version manager)
1.1 https://github.com/coreybutler/nvm-windows/releases pobrać i zainstalować nvm. [nvmsetup.zip]. Zresetować PC.
1.2 W terminalu: nvm install latest (pobranie najnowszego node.js)
1.3 nvm list (wylistowanie posiadanych wersji)
1.4 nvm use 7.7.3 (np. 7.7.3. Jeśli jest na pc tylko jedna wersja to ten krok jest nie jest potrzebny bo wykorzystywana jest ta przed ściągnięta w 1.2)
2. ANGULAR
2.1 npm install -g @angular/cli  [Jak nie idzie to Zresetować PC]
2.2 ng -v sprawdzenie wersji angulara

**Tworzenie swoich projektow w angularze (dla chętnych na przyszłość)
1. Utworzyc folder projektu
2. Otworzyc folder w konsoli
3. ng new NAZWAPROJEKTU
4. przejsc głębiej, np do app
5. ng serve [local 4200 do pracy nad aplikacją]
6* w razie potrzeby ng build --prod --aot. Wtedy w /dist utworzy się zbudowany projekt w postaci 6 plików projektu w wersji .min


Istotna struktura projektu
eventer
	ng2 (frontend)
		src
			app (aplikacja)
			assets (statyczne pliki np obrazy)
	src (backend)
		main
			java (aplikacja)
				db	(modele danych i repozytoria)
				logic (serwisy udostępniające i modyfikujące dane)
				api (kontrolery restowe)
				security (wszystko związane z spring security i tokenami JWT; LEPIJ NIE DOTYKAĆ)
				config (to co w nazwie; TEŻ LEPIJ NIE DOTYKAĆ)
			resources (statyczne zasoby dla apki)

--IMPORT PROJEKTU----------------------------------------------------------------------------------------------------------------
1. Utworzyc na dysku folder ktory bedzie lokalnym repo projektu. Wlaczyc idee i wybrac open folder.

2. Wybrac VCS->checkout from version controll->git

3. Git repository url: https://github.com/stachlew/eventer
Parent Directory: folder naszego lokalnego repo
Directory Name: eventer

4. Na pytanie czy utworzyc projekt idea, odpowiedzieć TAK i wybrać maven. Przeklikać się do końca. 
5. Na pytanie o okno wybrać aktualne (nie otwierać nowego)

6. Przełączyć lokalny branch na "production". [PRACA TYLKO NA PRODUCTION]. Z masterem będziemy łączyć kiedy indziej.

7. Wybrać File-> Project Structure -> Modules-> (+) -> wybrać sterownik bazy oracle ojdbc7.jar -> Apply

8. W klasie DemoRestController, w metodzie uploadImage() jest ścieżka do folderu do którego zapisują się zuploadowane pliki. Zmienić ją na swoje potrzeby. W późniejszej wersji zamiast na dysk pliki trafią do bazy i będzie to zbędne.

-- URUCHOMIENIE BACKENDU http://localhost:8080 (tylko api, nie ma widoków)
1. Przejść do klasy PzApplication -> Przycisk |> przy nazwie klasy -> RUN. 
-- backend powinien działać na porcie 8080
-- ostatni komunikat powinien być: "Started PzApplication in xx.xxx seconds"

-- FRONTEND
1. Uruchomić terminal w idei
2. Przejsc do folderu ng2 (cd ng2)

-- pobranie bibliotek angulara (tylko przy pierwszym uruchomieniu projektu)
3*. npm install
4*. dogranie bibliotek niestandardowych, dalej w terminalu:
npm install ng2-page-transition
npm install angular2-jwt
npm install angular2-ui-switch
npm i ng2-file-upload
npm i ng2-cookies

UPDATE[12.04.2017]
npm install angular2-google-maps 

-- uruchomienie frontendu (http://localhost:4200)
3. ng serve
4. Wyłączenie serwowania apki: W terminalu w którym została zaserwowana: CTRL+C, potem y

--tworzenie komponentow
Komponenty tworzy się komendą ng g TYPKOMPONENTU NAZWA przebywając na odpowiednim poziomie drzewa projektu w terminalu. Główny folder to APP.

-- INNE INFO
1. Na starcie istnieją 3 typy userów do korzystania:
gość bez zalogowania
user (password) [USER]
admin (admin) [USER,ADMIN]

2. Przy wysyłaniu żądań z angulara należy zwrócić uwagę na ich budowę. 
	W home.component.ts umieściłem różne konfiguracje metod http. Korzystają one z dodatkowego serwisu HttpSecService który udostępnia skonfigurowane dla naszej aplikacji opcje dla żądań
	
	W każdym żądaniu trzeba użyć zapisu this.myHttp.getUrl() jest to początek ścieżki w postaci http://localhost:8080 zadeklarowany w 1 miejscu by potem było łatwo zmienić port.
	
	Początek żądania i metod kontrolerów należy oznaczyć "/api" - ustawiony jest filtr akceptujący takie żądania na backu.
	
	Jeśli żądanie wymaga autoryzacji, to w kontrolerze musi być adnotacja (patrz: DemoRestController) i żądanie wysłane z angulara musi być oznaczone odpowiednią opcją:
	dla get jest to użycie:	this.myHttp.getConfig()
	a dla post: this.myHttp.postConfig()
	
	Oczywiście żeby mieć ten serwis z tą metodą należy wstrzyknąć w module w jego konstruktorze:
	np: 
	constructor(private http: Http, private myHttp: HttpSecService) {
		
	}
	
UWAGI:
Należy uważać by nie zaznaczyć node_modules jako folder do gita. To są biblioteki, które każde obsługuje lokalnie. 
	
	

