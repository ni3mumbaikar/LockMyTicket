package in.ni3mumbaikar.LockMyTicket.constant;

public final class QueryConstant {

    private QueryConstant() {
    }

    public static final class UserQueries {
        public static final String SELECT_USER_BY_USERNAME = "select * from lmt.lmt_user where user_name = ?";
        public static final String GET_ALL_USERS = "SELECT * FROM lmt.lmt_user";
        public static final String CREATE_NEW_USER = "INSERT INTO lmt.lmt_user (user_name,first_name,last_name, password, active) VALUES (?, ?, ?, ? ,?)";
        public static final String CHECK_IF_USER_EXISTS = "SELECT EXISTS(SELECT 1 FROM lmt.lmt_user WHERE user_name = ?)";

        private UserQueries() {
        }


    }
}
