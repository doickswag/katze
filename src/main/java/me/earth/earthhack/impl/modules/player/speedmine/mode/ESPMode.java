package me.earth.earthhack.impl.modules.player.speedmine.mode;

import me.earth.earthhack.impl.modules.player.speedmine.Speedmine;
import me.earth.earthhack.impl.util.math.MathUtil;
import me.earth.earthhack.impl.util.math.Vector3f;
import me.earth.earthhack.impl.util.render.RenderUtil;
import net.minecraft.util.math.AxisAlignedBB;

import java.awt.*;

public enum ESPMode
{
    None()
        {
            @Override
            public void drawEsp(Speedmine module, AxisAlignedBB bb, float damage, Color color1, Color color2)
            {
                /* None means no ESP. */
            }
        },
    Outline()
        {
            @Override
            public void drawEsp(Speedmine module, AxisAlignedBB bb, float damage, Color color1, Color color2)
            {
                RenderUtil.startRender();
                Vector3f color = MathUtil.mix(
                        new Vector3f(
                                (float)color1.getRed() / 255.0f,
                                (float)color1.getGreen() / 255.0f,
                                (float)color1.getBlue() / 255.0f
                        ),
                        new Vector3f(
                                (float)color2.getRed() / 255.0f,
                                (float)color2.getGreen() / 255.0f,
                                (float)color2.getBlue() / 255.0f
                        ),
                        damage
                );
                RenderUtil.drawOutline(bb, 1.5F,  new Color(color.x, color.y, color.z, (float)module.getBlockAlpha() / 255.0f));
                RenderUtil.endRender();
            }
        },
    Block()
        {
            @Override
            public void drawEsp(Speedmine module, AxisAlignedBB bb, float damage, Color color1, Color color2)
            {
                RenderUtil.startRender();
                Vector3f color = MathUtil.mix(
                        new Vector3f(
                                (float)color1.getRed() / 255.0f,
                                (float)color1.getGreen() / 255.0f,
                                (float)color1.getBlue() / 255.0f
                        ),
                        new Vector3f(
                                (float)color2.getRed() / 255.0f,
                                (float)color2.getGreen() / 255.0f,
                                (float)color2.getBlue() / 255.0f
                        ),
                        damage
                );
                System.out.println(color.x);
                System.out.println(color.y);
                System.out.println(color.z);
                RenderUtil.drawBox(bb, new Color(color.x, color.y, color.z, (float)module.getBlockAlpha() / 255.0f));
                RenderUtil.endRender();
            }
        },
    Box()
        {
            @Override
            public void drawEsp(Speedmine module, AxisAlignedBB bb, float damage, Color color1, Color color2)
            {
                Outline.drawEsp(module, bb, damage,color1,color2);
                Block.drawEsp(module, bb, damage,color1,color2);
            }
        };

    public abstract void drawEsp(Speedmine module, AxisAlignedBB bb, float damage, Color color1, Color color2);

}
