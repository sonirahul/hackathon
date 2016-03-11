package com.donateknowledge.aop;

import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE_DEFAULT;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donateknowledge.analytics.dto.Analytics;
import com.donateknowledge.analytics.dto.Keys;

@Aspect
@Component
public class DonateKnowledgeAspect {

	/*@Pointcut("within(com.donateknowledge.controller.*) || within(com.donateknowledge.dao.impl.*)")
	public void allMethodsPointcut(){}*/

	private static final Logger LOGGER = LoggerFactory.getLogger(DonateKnowledgeAspect.class);
	@Autowired Analytics keyWords;

	@Pointcut("execution(* com.donateknowledge.controller.DonateKnowledgeController.*(..))")
	public void allControllers(){}




	@Pointcut("execution(* com.donateknowledge.dao.ISessionDAO.*(..))")
	public void allSessionDAOPointCut(){}

	@Pointcut("execution(* com.donateknowledge.dao.ISessionDAO.findUserEmailBySessionId(..))")
	public void existingUserPointCut1(){}

	@Pointcut("allSessionDAOPointCut() && !existingUserPointCut1()")
	public void allSessionDAOButExistingUserPointCut1(){}




	/*@Pointcut("execution(* com.donateknowledge.dao.ICellPhoneDAO.*(..))")
	public void allCellPhoneDAOPointCut(){}

	@Pointcut("execution(* com.donateknowledge.dao.ICellPhoneDAO.fetchCellPhoneById(..))")
	public void fetchCellPhoneById(){}

	@Pointcut("allCellPhoneDAOPointCut() && !fetchCellPhoneById()")
	public void allCellPhoneDAOButFetchCellPhoneById(){}*/




	@Pointcut("execution(* com.donateknowledge.dao.IUserDAO.*(..))")
	public void allUserDAOPointCut(){}

	@Pointcut("execution(* com.donateknowledge.dao.IUserDAO.validateUser(..))")
	public void existingUserPointCut2(){}

	@Pointcut("allUserDAOPointCut() && !existingUserPointCut2()")
	public void allUserDAOButExistingUserPointCut2(){}




	@Pointcut("execution(* com.donateknowledge.service.IDonateKnowledgeService.getLoggedInUser(..))")
	public void newUserPointCut(){}



	/*@Around("allControllers() || allSessionDAOButExistingUserPointCut1() || allUserDAOButExistingUserPointCut2() || allCellPhoneDAOButFetchCellPhoneById()")
	public Object applicationAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		Object value = logMethodExecution(proceedingJoinPoint);
		return value;
	}*/



	/*@Around("fetchCellPhoneById()")
	public Object fetchCellPhoneByIdAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object value = logMethodExecution(proceedingJoinPoint);

		for (Object itr : proceedingJoinPoint.getArgs()) {
			keyWords.getKeyWords().add(new Keys((String) itr, value == null ? false : true));
		}
		return value;
	}*/



	@Around("newUserPointCut()")
	public Object newUserAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object value = logMethodExecution(proceedingJoinPoint);
		//HttpSession
		if (SESSION_COOKIE_DEFAULT.equals(String.valueOf(proceedingJoinPoint.getArgs()[0]))) {
			keyWords.getUser().get(0).addNewUserLogin(BigInteger.ONE);
		}
		else {
			
		}
		return value;
	}



	@Around("existingUserPointCut1() || existingUserPointCut2()")
	public Object existingUserAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object value = logMethodExecution(proceedingJoinPoint);

		keyWords.getUser().get(0).addExistingUserCounts(BigInteger.ONE);
		return value;
	}



	private Object logMethodExecution(ProceedingJoinPoint proceedingJoinPoint) {
		String methodSig=proceedingJoinPoint.toString().substring(proceedingJoinPoint.toString().indexOf("("));
		LOGGER.debug("Executing method=" + methodSig + 
				", Arguments passed=" + Arrays.toString(proceedingJoinPoint.getArgs()));
		Object value = null;
		Date before = null;
		Date after = null;
		try {
			before = new Date();
			value = proceedingJoinPoint.proceed();
			after = new Date();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long timeTaken = after.getTime() - before.getTime();
		LOGGER.debug("Executed method=" + methodSig +  ", Return value="+ value +
				", Time taken=" + timeTaken);
		return value;
	}
}
