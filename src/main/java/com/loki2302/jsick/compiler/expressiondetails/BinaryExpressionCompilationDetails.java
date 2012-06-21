package com.loki2302.jsick.compiler.expressiondetails;

import com.loki2302.jsick.types.JType;

public class BinaryExpressionCompilationDetails extends ExpressionCompilationDetails {
	
	private final ExpressionCompilationDetails leftDetails;
	private final ExpressionCompilationDetails rightDetails;
	private final CommonnessKind commonnessKind;
	
	public BinaryExpressionCompilationDetails(
			ExpressionCompilationDetails leftDetails, 
			ExpressionCompilationDetails rightDetails,
			JType type,
			CommonnessKind commonnessKind) {
		super(type);
		this.leftDetails = leftDetails;
		this.rightDetails = rightDetails;
		this.commonnessKind = commonnessKind;
	}

	@Override
	public boolean hasErrors() {
		return 	leftDetails.hasErrors() || 
				rightDetails.hasErrors() ||
				commonnessKind == CommonnessKind.None;
	}		
	
	public CommonnessKind getCommonnessKind() {
		return commonnessKind;
	}
	
	public static BinaryExpressionCompilationDetails passThrough(
			ExpressionCompilationDetails leftDetails, 
			ExpressionCompilationDetails rightDetails) {
		return new BinaryExpressionCompilationDetails(leftDetails, rightDetails, null, null);
	}
	
	public static BinaryExpressionCompilationDetails directly(
			ExpressionCompilationDetails leftDetails, 
			ExpressionCompilationDetails rightDetails,
			JType commonType) {
		return new BinaryExpressionCompilationDetails(leftDetails, rightDetails, commonType, CommonnessKind.Directly);
	}
	
	public static BinaryExpressionCompilationDetails implicitlyCastLeftTo(
			ExpressionCompilationDetails leftDetails, 
			ExpressionCompilationDetails rightDetails,
			JType commonType) {
		return new BinaryExpressionCompilationDetails(leftDetails, rightDetails, commonType, CommonnessKind.CastLeft);
	}
	
	public static BinaryExpressionCompilationDetails implicitlyCastRightTo(
			ExpressionCompilationDetails leftDetails, 
			ExpressionCompilationDetails rightDetails,
			JType commonType) {
		return new BinaryExpressionCompilationDetails(leftDetails, rightDetails, commonType, CommonnessKind.CastRight);
	}
	
	public static BinaryExpressionCompilationDetails noCommonType(
			ExpressionCompilationDetails leftDetails, 
			ExpressionCompilationDetails rightDetails) {
		return new BinaryExpressionCompilationDetails(leftDetails, rightDetails, null, CommonnessKind.None);
	}
	
	public enum CommonnessKind {
		Directly,
		CastLeft,
		CastRight,
		None
	}
}