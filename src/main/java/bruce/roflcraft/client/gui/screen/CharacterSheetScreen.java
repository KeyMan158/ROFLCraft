package bruce.roflcraft.client.gui.screen;

import bruce.roflcraft.client.gui.component.GUIComponentManager;
import bruce.roflcraft.client.gui.component.GUIComponentScreen;
import bruce.roflcraft.client.gui.component.character.AtributeDialComponent;
import bruce.roflcraft.client.gui.component.character.AttributeFrame;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;

/**
 * This class is the root for the charater screen
 * @author Lorrtath
 *
 */
public class CharacterSheetScreen extends GUIComponentScreen
{
	public CharacterSheetScreen()
	{
		super();
		GUIComponentManager panables = new GUIComponentManager();
		registerComponent(panables);
		AtributeDialComponent dial = new AtributeDialComponent();
		panables.register(dial);
		
		AttributeFrame test = new AttributeFrame(AttributeIndex.AT_BODY);
		panables.register(test);
	}
}
