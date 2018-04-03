package bruce.roflcraft.gui.GUIComponent.CharacterSheet;

import bruce.roflcraft.gui.GUIComponent.GUIComponentManager;
import bruce.roflcraft.gui.GUIComponent.GUIComponentScreen;
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
