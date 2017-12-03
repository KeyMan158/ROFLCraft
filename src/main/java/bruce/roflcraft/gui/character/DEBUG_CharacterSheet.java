package bruce.roflcraft.gui.character;

import bruce.roflcraft.gui.GUIComponent.GUIComponentManager;
import bruce.roflcraft.gui.GUIComponent.Button.BuySkillPointButton;
import bruce.roflcraft.gui.GUIComponent.Button.SkillIncreaseButton;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * Character sheet for debugging and testing purposes and is intended to provide
 * basic functionality without the artwork of the final character sheet
 * @author Lorrtath
 */
public class DEBUG_CharacterSheet extends GuiScreen
{
	private RPGCharacterData m_characterData;
	private GUIComponentManager m_componentManager;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		m_componentManager.drawComponent(Minecraft.getMinecraft(), mouseX, mouseY);
		fontRenderer.drawStringWithShadow("Name : ", 16, 0, 16777215);
		fontRenderer.drawStringWithShadow("Level : " + m_characterData.getSkillPointTracker().getLevel(), 16, 16, 16777215);
		fontRenderer.drawStringWithShadow("Progress To Next : " + m_characterData.getSkillPointTracker().getStoredXP() + " / " + m_characterData.getSkillPointTracker().xpToNext(), 16, 32, 16777215);
		fontRenderer.drawStringWithShadow("Points Available : " + m_characterData.getSkillPointTracker().getAvailablePoints(), 16, 48, 16777215);
		int attTop = 80;
		AttributeIndex[] attrIndexes = AttributeIndex.values();
		for(int i = 0; i < m_characterData.getAttributes().count(); i++)
		{
			fontRenderer.drawStringWithShadow(m_characterData.getAttributes().getStatName(i) + " : " + 
					m_characterData.getAttributes().getStatValue(i) + " (" +
					m_characterData.getAttributes().getAttributeProgress(attrIndexes[i])+ "/3)", 16, attTop, 16777215);
			attTop += 16;
		}
		int buttonTop = 0;
		for(int i = 0; i < m_characterData.getSkills().count(); i++)
		{
			fontRenderer.drawStringWithShadow(m_characterData.getSkills().getStatName(i) + " : " + m_characterData.getSkills().getStatValue(i), 216, buttonTop, 16777215);
			buttonTop += 16;
		}
	}
	
	@Override
	public void updateScreen()
	{
		
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void initGui()
	{
		m_characterData = (RPGCharacterData)Minecraft.getMinecraft().player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		m_componentManager = new GUIComponentManager();
		BuySkillPointButton buyPointButton = new BuySkillPointButton();
		buyPointButton.setTop(30);
		m_componentManager.register(buyPointButton);
		int buttonTop = 0;
		SkillIndex[] indexes = SkillIndex.values();
		for(int i = 0; i < m_characterData.getSkills().count(); i++)
		{
			SkillIncreaseButton skillButton = new SkillIncreaseButton();
			skillButton.setSkillIndex(indexes[i]);
			skillButton.setTop(buttonTop);
			skillButton.setLeft(200);
			m_componentManager.register(skillButton);
			buttonTop += 16;
		}
		m_componentManager.init(0, 0);
	}
}
