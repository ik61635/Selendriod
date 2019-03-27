package com.selendriod.demo.objectrespository;

public class Locators {
	public static class Home {
		public static String visibleBtn = "id__visibleButtonTest";
		public static String visibleTxt = "id__visibleTextView";
		public static String topLevelElementBtn="id__topLevelElementTest";
		public static String focusedTxt="id__focusedText";
		public static String startUserRegistrationBtn="id__startUserRegistration";
		public static String buttonStartWebviewBtn="id__buttonStartWebview";
	}

	public static class Web {
		public static String name_inputEdt = "id__name_input";
		public static String carSelect = "name__car";
		public static String text1Radio = "id__text1";
		public static String submitBtn = "xpath__//input[contains(@type,'submit')]";
		public static String titleLable = "xpath__//title[contains(.,'John')]";
		
	}

	public static class Registration {
		public static String usernameEdt = "id__label_username";
		public static String inputEmailEdt = "id__inputEmail";
		public static String inputPasswordEdt = "id__inputPassword";
		public static String inputNameEdt = "id__inputName";
		public static String input_addsChckBx = "id__input_adds";
		public static String input_preferedProgrammingLanguageSelectBx = "id__input_preferedProgrammingLanguage";
		public static String text1Radio = "id__text1";
		public static String btnRegisterUserBtn = "id__btnRegisterUser";
		public static String label_name_dataLbl = "id__label_name_data";
		
	}

}

