package com.vp.plugin.sample.sequenceDiagramPlugin;

import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IDiagramTypeConstants;
import com.vp.plugin.diagram.IInteractionDiagramUIModel;
import com.vp.plugin.diagram.connector.IMessageUIModel;
import com.vp.plugin.diagram.shape.IActivationUIModel;
import com.vp.plugin.diagram.shape.ICombinedFragmentUIModel;
import com.vp.plugin.diagram.shape.IInteractionLifeLineUIModel;
import com.vp.plugin.diagram.shape.IInteractionOperandUIModel;
import com.vp.plugin.diagram.shape.ILostFoundMessageEndShapeUIModel;
import com.vp.plugin.model.IActivation;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.ICombinedFragment;
import com.vp.plugin.model.IFrame;
import com.vp.plugin.model.IInteractionConstraint;
import com.vp.plugin.model.IInteractionLifeLine;
import com.vp.plugin.model.IInteractionOperand;
import com.vp.plugin.model.IMessage;
import com.vp.plugin.model.IModel;
import com.vp.plugin.model.factory.IModelElementFactory;

public class SequenceDiagramAction implements VPActionController{

	@Override
	public void performAction(VPAction arg0) {
		//Create blank diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IInteractionDiagramUIModel sequence = (IInteractionDiagramUIModel) diagramManager.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_INTERACTION_DIAGRAM);
		sequence.setName("Sample Sequence Diagram");
		// Retrieve root frame of the interaction
		IFrame rootFrame = sequence.getRootFrame(true);
		
		//Create Model
		IModel model = IModelElementFactory.instance().createModel();
		model.setName("diagram model");
		model.addSubDiagram(sequence);
		
		
		//Create Classes
		IClass classOrder = IModelElementFactory.instance().createClass();
		classOrder.setName("Order");
		classOrder.setVisibility(IClass.VISIBILITY_PUBLIC);
		model.addChild(classOrder);
		
		IClass classDistributor = IModelElementFactory.instance().createClass();
		classDistributor.setName("Distributor");
		classDistributor.setVisibility(IClass.VISIBILITY_PUBLIC);
		model.addChild(classDistributor);
		
		IClass classMesssenger = IModelElementFactory.instance().createClass();
		classMesssenger.setName("Messenger");
		classMesssenger.setVisibility(IClass.VISIBILITY_PUBLIC);
		model.addChild(classMesssenger);
		
		//Create Lifelines
		IInteractionLifeLine lifelineOrder = IModelElementFactory.instance().createInteractionLifeLine();
		// Add lifeline to root frame
		rootFrame.addChild(lifelineOrder);
		lifelineOrder.setName("");
		//declare which class does this lifeline associates to
		lifelineOrder.setBaseClassifier(classOrder);
		//Create shape on diagram
		IInteractionLifeLineUIModel shapeOrder = (IInteractionLifeLineUIModel) diagramManager.createDiagramElement(sequence, lifelineOrder);
		shapeOrder.setBounds(200, 25, 100, 550);
		shapeOrder.resetCaption();
		
		IInteractionLifeLine lifelineCareful = IModelElementFactory.instance().createInteractionLifeLine();
		rootFrame.addChild(lifelineCareful);
		lifelineCareful.setName("Careful");
		lifelineCareful.setBaseClassifier(classDistributor);
		IInteractionLifeLineUIModel shapeCareful = (IInteractionLifeLineUIModel) diagramManager.createDiagramElement(sequence, lifelineCareful);
		shapeCareful.setBounds(350, 25, 100, 550);
		shapeCareful.resetCaption();
		
		IInteractionLifeLine lifelineRegular = IModelElementFactory.instance().createInteractionLifeLine();
		rootFrame.addChild(lifelineRegular);
		lifelineRegular.setName("Regular");
		lifelineRegular.setBaseClassifier(classDistributor);
		IInteractionLifeLineUIModel shapeRegular = (IInteractionLifeLineUIModel) diagramManager.createDiagramElement(sequence, lifelineRegular);
		shapeRegular.setBounds(500, 25, 100, 550);
		shapeRegular.resetCaption();
		
		IInteractionLifeLine lifelineMessenger = IModelElementFactory.instance().createInteractionLifeLine();
		rootFrame.addChild(lifelineMessenger);
		lifelineMessenger.setName("");
		lifelineMessenger.setBaseClassifier(classMesssenger);
		IInteractionLifeLineUIModel shapeMessenger = (IInteractionLifeLineUIModel) diagramManager.createDiagramElement(sequence, lifelineMessenger);
		shapeMessenger.setBounds(650, 25, 100, 550);
		shapeMessenger.resetCaption();
		
		//Create activation
		IActivation activationOrder = IModelElementFactory.instance().createActivation();
		//make this activation a part of the life line Order
		lifelineOrder.addActivation(activationOrder);
		//Create shape on diagram
		IActivationUIModel shapeActivationOrder = (IActivationUIModel) diagramManager.createDiagramElement(sequence, activationOrder);
		shapeActivationOrder.setBounds(246, 100, IActivationUIModel.BODY_WIDTH, 400);
		
		IActivation activationCareful = IModelElementFactory.instance().createActivation();
		lifelineCareful.addActivation(activationCareful);
		IActivationUIModel shapeActivationCareful = (IActivationUIModel) diagramManager.createDiagramElement(sequence, activationCareful);
		shapeActivationCareful.setBounds(396, 250, IActivationUIModel.BODY_WIDTH, 10);
		
		IActivation activationRegular = IModelElementFactory.instance().createActivation();
		lifelineRegular.addActivation(activationRegular);
		IActivationUIModel shapeActivationRegular = (IActivationUIModel) diagramManager.createDiagramElement(sequence, activationRegular);
		shapeActivationRegular.setBounds(546, 300, IActivationUIModel.BODY_WIDTH, 10);
		
		IActivation activationMessenger = IModelElementFactory.instance().createActivation();
		lifelineMessenger.addActivation(activationMessenger);
		IActivationUIModel shapeActivationMesenger = (IActivationUIModel) diagramManager.createDiagramElement(sequence, activationMessenger);
		shapeActivationMesenger.setBounds(696, 450, IActivationUIModel.BODY_WIDTH, 10);
		
		

		//Create operands
		IInteractionOperand eachItem = IModelElementFactory.instance().createInteractionOperand();
		eachItem.setName("each Item");
		//Create constraint
		IInteractionConstraint constraintEachItem = IModelElementFactory.instance().createInteractionConstraint();
		constraintEachItem.setName("for each item");
		constraintEachItem.setConstraint("for each item");
		//add the constraint into the operand
		eachItem.setGuard(constraintEachItem);
		//Create shape on diagram
		IInteractionOperandUIModel shapeEachItem = (IInteractionOperandUIModel) diagramManager.createDiagramElement(sequence, eachItem);
		
		IInteractionOperand value = IModelElementFactory.instance().createInteractionOperand();
		value.setName("val > 10,000");
		IInteractionConstraint constraintValue = IModelElementFactory.instance().createInteractionConstraint();
		constraintValue.setName("value > $10,000");
		constraintValue.setConstraint("value > $10,000");
		value.setGuard(constraintValue);
		IInteractionOperandUIModel shapeValue = (IInteractionOperandUIModel) diagramManager.createDiagramElement(sequence, value);
		shapeValue.addChild(shapeActivationCareful);
		
		
		IInteractionOperand valueElse = IModelElementFactory.instance().createInteractionOperand();
		valueElse.setName("else");
		IInteractionConstraint constraintValueElse = IModelElementFactory.instance().createInteractionConstraint();
		constraintValueElse.setName("else");
		constraintValueElse.setConstraint("else");
		valueElse.setGuard(constraintValueElse);
		IInteractionOperandUIModel shapeValueElse = (IInteractionOperandUIModel) diagramManager.createDiagramElement(sequence, valueElse);
		shapeValueElse.addChild(shapeActivationRegular);
		
		IInteractionOperand needsConfirmation = IModelElementFactory.instance().createInteractionOperand();
		needsConfirmation.setName("needs confirmation");
		IInteractionConstraint constraintNeedsConfirmation = IModelElementFactory.instance().createInteractionConstraint();
		constraintNeedsConfirmation.setName("needs confirmation");
		constraintNeedsConfirmation.setConstraint("needs confirmation");
		needsConfirmation.setGuard(constraintNeedsConfirmation);
		IInteractionOperandUIModel shapeNeedsConfirmation = (IInteractionOperandUIModel) diagramManager.createDiagramElement(sequence, needsConfirmation);
		shapeNeedsConfirmation.addChild(shapeActivationMesenger);
		
		
		//Create Combine fragments
		ICombinedFragment fragmentEachItem = IModelElementFactory.instance().createCombinedFragment();
		fragmentEachItem.setName("Loop for each item");
		rootFrame.addChild(fragmentEachItem);
		//This is a loop
		fragmentEachItem.setInteractionOperator(ICombinedFragment.INTERACTION_OPERATOR_LOOP);
		//Give the fragment an operand
		fragmentEachItem.addOperand(eachItem);
		//this fragment will cover three life lines
		fragmentEachItem.addCoveredLifeLine(lifelineOrder);
		fragmentEachItem.addCoveredLifeLine(lifelineCareful);
		fragmentEachItem.addCoveredLifeLine(lifelineRegular);
		//Create shape on diagram
		ICombinedFragmentUIModel shapeFragmentEachItem = (ICombinedFragmentUIModel) diagramManager.createDiagramElement(sequence, fragmentEachItem);
		shapeFragmentEachItem.setBounds(50, 150, 575, 225);
		//add the operand shape as child of the fragment
		shapeFragmentEachItem.addChild(shapeEachItem);
		shapeFragmentEachItem.resetCaption();
		
		
		ICombinedFragment fragmentValue = IModelElementFactory.instance().createCombinedFragment();
		fragmentEachItem.addChild(fragmentValue);
		fragmentValue.setName("Val > 10k");
		fragmentValue.setInteractionOperator(ICombinedFragment.INTERACTION_OPERATOR_ALT);
		fragmentValue.addOperand(value);
		fragmentValue.addOperand(valueElse);
		fragmentValue.addCoveredLifeLine(lifelineOrder);
		fragmentValue.addCoveredLifeLine(lifelineCareful);
		fragmentValue.addCoveredLifeLine(lifelineRegular);
		eachItem.addChild(fragmentValue);
		ICombinedFragmentUIModel shapeFragmentValue = (ICombinedFragmentUIModel) diagramManager.createDiagramElement(sequence, fragmentValue);
		shapeFragmentValue.setBounds(75, 200, 525, 150);
		shapeFragmentValue.addChild(shapeValue);
		shapeFragmentValue.addChild(shapeValueElse);
		shapeFragmentValue.resetCaption();
		shapeEachItem.addChild(shapeFragmentValue);
		
		ICombinedFragment fragmentConfirmation = IModelElementFactory.instance().createCombinedFragment();
		fragmentConfirmation.setName("needs confirmation");
		rootFrame.addChild(fragmentConfirmation);
		fragmentConfirmation.setInteractionOperator(ICombinedFragment.INTERACTION_OPERATOR_ALT);
		fragmentConfirmation.addOperand(needsConfirmation);
		fragmentConfirmation.addCoveredLifeLine(lifelineOrder);
		fragmentConfirmation.addCoveredLifeLine(lifelineCareful);
		fragmentConfirmation.addCoveredLifeLine(lifelineRegular);
		fragmentConfirmation.addCoveredLifeLine(lifelineMessenger);
		ICombinedFragmentUIModel shapeFragmentConfirmation = (ICombinedFragmentUIModel) diagramManager.createDiagramElement(sequence, fragmentConfirmation);
		shapeFragmentConfirmation.setBounds(50, 400, 700, 75);
		shapeFragmentConfirmation.addChild(shapeNeedsConfirmation);
		shapeFragmentConfirmation.resetCaption();
		
		
		
		//Create found message
		//Create found/lost shape
		ILostFoundMessageEndShapeUIModel found = (ILostFoundMessageEndShapeUIModel) sequence.createDiagramElement(IInteractionDiagramUIModel.SHAPETYPE_LOST_FOUND_MESSAGE_END);
		found.setBounds(140, 90, 20, 20);
		//Create message
		IMessage message1 = IModelElementFactory.instance().createMessage();
		message1.setName("dispatch");
		message1.setSequenceNumber("1");
		//This message will connect to the activation on the Order life line
		message1.setToActivation(activationOrder);
		//Create shape on diagram
		IMessageUIModel shapeMessage1 = (IMessageUIModel) diagramManager.createConnector(sequence, message1, found, shapeOrder, new Point[] {new Point(160,100), new Point(246,100)});
		shapeMessage1.resetCaption();
		
		
		//Create messages
		IMessage message1_1 = IModelElementFactory.instance().createMessage();
		message1_1.setName("diapatch");
		message1_1.setSequenceNumber("1.1");
		//This message will connect from the activation on the Order time line...
		message1_1.setFromActivation(activationOrder);
		//... to the Careful time line.
		message1_1.setToActivation(activationCareful);
		//make the message a child of the value > operand such that the message will move with the operand's combined fragment
		value.addMessage(message1_1);
		//Create shape on diagram
		IMessageUIModel shapeMessage1_1 = (IMessageUIModel) diagramManager.createConnector(sequence, message1_1, shapeOrder, shapeCareful, new Point[] {new Point(254,250), new Point(396,250)});
		shapeMessage1_1.resetCaption();
		
		IMessage message1_2 = IModelElementFactory.instance().createMessage();
		message1_2.setName("diapatch");
		message1_2.setSequenceNumber("1.2");
		message1_2.setFromActivation(activationOrder);
		message1_2.setToActivation(activationRegular);
		valueElse.addMessage(message1_2);
		IMessageUIModel shapeMessage1_2 = (IMessageUIModel) diagramManager.createConnector(sequence, message1_2, shapeOrder, shapeRegular, new Point[] {new Point(254,300), new Point(546,300)});
		shapeMessage1_2.resetCaption();
		
		IMessage message1_3 = IModelElementFactory.instance().createMessage();
		message1_3.setName("confirm");
		message1_3.setSequenceNumber("1.3");
		message1_3.setFromActivation(activationOrder);
		message1_3.setToActivation(activationMessenger);
		needsConfirmation.addMessage(message1_3);
		IMessageUIModel shapeMessage1_3 = (IMessageUIModel) diagramManager.createConnector(sequence, message1_3, shapeOrder, shapeMessenger, new Point[] {new Point(254,450), new Point(696,450)});
		shapeMessage1_3.resetCaption();
		
		
		//show diagram
		diagramManager.openDiagram(sequence);
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
