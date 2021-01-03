package jwt.util;

public class CommonConstants {

	/*
	Admin and User Routes
	 */
	public final static String ROUTE_ADMIN ="/admin/";
	public final static String ROUTE_USER ="/user";

	/*
	 * Route constants
	 */
	///main
	public final static String ROUTE_ADMIN_MAIN = "/api/admin";
	public final static String ROUTE_ENGINEER_MAIN = "/api/engineer";
	public final static String ROUTE_PROPERTY_MAIN = "/api/property";
	public final static String ROUTE_EMPLOYEE_MAIN = "/api/employee";
	public final static String ROUTE_USER_MAIN = "/api/user";
	public static final String ROUTE_JOBCARD_MAIN = "/api/jobcard";
	public static final String ROUTE_MAINTAINER_MAIN = "/api/maintainer";
	public final static String ROUTE_AUTH_MAIN = "/api/auth";
	public static final String ROUTE_RATE_CARD_ITEM_MAIN = "/api/ratecarditem";
	public static final String ROUTE_RATE_CARD_CATEGORY_MAIN = "/api/ratecardcategory";
	public static final String ROUTE_MAINTAINCE_INQUIRY_MAIN = "/api/maintainceinquiry";
	public static final String ROUTE_RATE_MAIN = "/api/rate";
	public static final String ROUTE__ASSIGN_MAINTAINER = "/api/assignmaintainer";
	public static final String ROUTE_APARTMENT_USER_MAIN =  "/api/apartmentuser";
	public static final String ROUTE_PERMISSION_MAIN = "/api/permission";
	public static final String ROUTE_PROPERTY_MANAGER_MAIN = "/api/propertymanager";
	public static final String ROUTE_OPERATIONAL_MANAGER_MAIN = "/api/operationalmanager";
	public static final String ROUTE_HELP_DESK_USER_MAIN = "/api/helpdeskuser";




	///common
	public final static String ROUTE_CREATE = "/create";
	public final static String ROUTE_UPDATE = "/update";
	public final static String ROUTE_ALL = "/fetch/all";
	public static final String ROUTE_ALL_TENANT = "/fetchtenant/all";
	public static final String ROUTE_ALL_OWNER = "/fetchowner/all";
	public static final String ROUTE_ASSIGN_APARTMENT_USER = "/assignapartmentuser";
	public static final String ROUTE_REMOVE_USER_FROM_PROPERTY ="/remove/{propertyId}/{userId}" ;
	public static final String ROUTE_ASSIGN_ENGINEER_TO_RATE_CARD_CATEGORY = "/assignengineertoratecardcategory";


	public final static String ROUTE_COUNT = "/fetchcount";
	public final static String ROUTE_SINGLE = "/fetch/{id}";

	/*Pagination routes
	 *
	 */
	public final static String ROUTE_PAGINATION_SINGLE = "/fetch/{searchField}/{sortType}/{page}/{limit}";
	public final static String ROUTE_PAGINATION_EMPLOYEE = "/fetch/{empType}/{searchField}/{sortType}/{page}/{limit}";
	public final static String ROUTE_PAGINATION_APARTMENT_USER = "/fetch/{userType}/{searchField}/{sortType}/{page}/{limit}";
	public static final String ROUTE_PAGINATION_PROPERTY_ALL_FIELDS = "/fetch/{address}/{floor}/{user}/{page}/{limit}";





	/*
	 * Authentication constants
	 */
	public final static String TEST_USERNAME = "nova";
	public final static String TEST_PASSWORD = "$2y$12$1wfQo7aRc5yfoZCi39ORfO3/4qNwQAw4OB3tnyIPqL0a9DkZXnCvS"; //equivalent to nova@123
	public final static String MESSAGE_UNAUTHORIZED = "Unauthorized";
	public final static String HEADER_AUTHORIZATION = "Authorization";
	public final static long JWT_RESPONSE_SERIAL_VER_UID = -8091879091924046844L;
	public final static long JWT_REQUEST_SERIAL_VER_UID = 5926468583005150707L;
	public final static long JWT_TOKEN_UTIL_SERIAL_VER_UID = -2550185165626007488L;
	public final static long JWT_AUTH_ENTRY_POINT_SERIAL_VER_UID = -7858869558953243875L;


    public static final Object MESSAGE_ERROR_USER_NULL = "User not found !";


	public final static String SYSTEM_USER_ADMIN = "Admin";
	public final static String SYSTEM_USER_PROPERTY_MANAGER = "Property Manager";
	public final static String SYSTEM_USER_OPERATIONAL_MANAGER = "Operational Manager";
	public final static String SYSTEM_USER_HELP_DESK_USER = "Help Desk User";
	public final static String SYSTEM_USER_ENGINEER = "Engineer";
	public final static String SYSTEM_USER_OWNER = "Owner";
	public final static String SYSTEM_USER_TENANT = "Tenant";
	public final static String SYSTEM_USER_MAINTAINER = "Maintainer";
	public final static String SYSTEM_USER_SECURITY = "Security";
}
