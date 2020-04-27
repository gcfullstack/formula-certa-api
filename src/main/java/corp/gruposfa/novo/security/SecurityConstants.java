package corp.gruposfa.novo.security;

public class SecurityConstants {

	public static final String SECRET = "6402cf55fda627cafed908cfdbea7333";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/users/sign-up";
	public static final String PUBLIC_URL = "/public/**";
	public static final String CLAIM_PERMISSOES = "permissoes";
	public static final String CLAIM_ID_USER = "id";
	public static final String CLAIM_USER_LOGADO = "nome";
	public static final String CLAIM_AREA = "area";

}
