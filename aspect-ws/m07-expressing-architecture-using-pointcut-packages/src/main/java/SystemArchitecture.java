import org.aspectj.lang.annotation.Pointcut;

public class SystemArchitecture {

	// any class in subpackage repository of com.mujahed
	@Pointcut("execution(* com.mujahed..repository.*.*(..))")
	public void Repository() {

	}

	@Pointcut("execution(* com.mujahed..service.*.*(..))")
	public void Service() {

	}
}
