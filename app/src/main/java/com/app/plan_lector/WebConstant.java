package com.app.plan_lector;

public interface WebConstant {


	int SPLASH_TIMEOUT = 3000;
	int SPLASH_PROGRESS_TIME = 2000;

	// WEB SERVICE RESULT CODE
	int RESULT_SUCCESS = 1;
	int RESULT_ERROR_EMAIL_ID_EXIST = 20;
	int RESULT_ERROR_EMAIL_ID_DOESNT_EXIST = 30;
	int RESULT_ERROR_WRONG_CREDENTIALS = 40;
	int RESULT_ERROR_BLANK_GCM_ID = 50;
	int RESULT_ERROR_INVALID_AUTH_TOKEN = 100;
	String sharedpreferencesname = "PREF";

	int RESPONSE_CODE_FORBIDDEN = 403;
	int RESPONSE_CODE_RESOURCE_NOT_FOUND = 404;
	int RESPONSE_CODE_SERVER_ERROR = 500;
	int ACTIVITY_MARK_TASK = 110;


}
