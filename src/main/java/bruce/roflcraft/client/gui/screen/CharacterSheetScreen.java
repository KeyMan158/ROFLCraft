package bruce.roflcraft.client.gui.screen;

import bruce.roflcraft.client.gui.component.GUIComponentBase;
import bruce.roflcraft.client.gui.component.GUIComponentManager;
import bruce.roflcraft.client.gui.component.GUIComponentScreen;
import bruce.roflcraft.client.gui.component.button.BuySkillPointButton;
import bruce.roflcraft.client.gui.component.character.AtributeDialComponent;
import bruce.roflcraft.client.gui.component.character.AttributeFrame;
import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
import bruce.roflcraft.client.gui.component.utl.HorizontalAlignment;
import bruce.roflcraft.client.gui.component.utl.VerticalAlignment;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import net.minecraft.util.ResourceLocation;

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
		
		BuySkillPointButton skillPointButton = new BuySkillPointButton();
		panables.register(skillPointButton);
		
		AttributeFrame bodyFrame = new AttributeFrame(AttributeIndex.AT_BODY);
		bodyFrame.setTop(64);
		bodyFrame.setLeft(0);
		bodyFrame.registerListener(dial);
		panables.register(bodyFrame);
		
		AttributeFrame mindFrame = new AttributeFrame(AttributeIndex.AT_SOUL);
		mindFrame.setTop(-32);
		mindFrame.setLeft(55);
		mindFrame.registerListener(dial);
		panables.register(mindFrame);
		
		AttributeFrame soulFrame = new AttributeFrame(AttributeIndex.AT_MIND);
		soulFrame.setTop(-32);
		soulFrame.setLeft(-55);
		soulFrame.registerListener(dial);
		panables.register(soulFrame);
	}
}
