package com.coders.screen;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PageTurnWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class PortableStorageInterfaceScreen extends Screen {

    private PageTurnWidget nextPageButton;
    private PageTurnWidget previousPageButton;
    protected static final int MAX_TEXT_WIDTH = 114;
    protected static final int MAX_TEXT_HEIGHT = 128;
    protected static final int WIDTH = 192;
    protected static final int HEIGHT = 192;
    private BookScreen.Contents contents;
    private int pageIndex;
    private List<OrderedText> cachedPage;
    private int cachedPageIndex;
    private Text pageIndexText;

    private final boolean pageTurnSound;
    protected PortableStorageInterfaceScreen(Text title, boolean playPageTurnSound)
         {
            super(NarratorManager.EMPTY);
            this.cachedPage = Collections.emptyList();
            this.cachedPageIndex = -1;
            this.pageIndexText = ScreenTexts.EMPTY;
            this.contents = contents;
            this.pageTurnSound = playPageTurnSound;
    }



    @Override
    protected void init() {
        this.addCloseButton();
        this.addPageButtons();
    }

    protected void addCloseButton() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, 196, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen((Screen)null);
        }));
    }

    protected void addPageButtons() {
        int i = (this.width - 192) / 2;
        boolean j = true;
        this.nextPageButton = (PageTurnWidget)this.addDrawableChild(new PageTurnWidget(i + 116, 159, true, (button) -> {
            this.goToNextPage();
        }, this.pageTurnSound));
        this.previousPageButton = (PageTurnWidget)this.addDrawableChild(new PageTurnWidget(i + 43, 159, false, (button) -> {
            this.goToPreviousPage();
        }, this.pageTurnSound));
        this.updatePageButtons();
    }

    private int getPageCount() {
        return this.contents.getPageCount();
    }

    protected void goToPreviousPage() {
        if (this.pageIndex > 0) {
            --this.pageIndex;
        }

        this.updatePageButtons();
    }

    protected void goToNextPage() {
        if (this.pageIndex < this.getPageCount() - 1) {
            ++this.pageIndex;
        }

        this.updatePageButtons();
    }

    private void updatePageButtons() {
        this.nextPageButton.visible = this.pageIndex < this.getPageCount() - 1;
        this.previousPageButton.visible = this.pageIndex > 0;
    }
    protected void closeScreen() {
        this.client.setScreen((Screen)null);
    }


    @Nullable
    public Style getTextStyleAt(double x, double y) {
        if (this.cachedPage.isEmpty()) {
            return null;
        } else {
            int i = MathHelper.floor(x - (double)((this.width - 192) / 2) - 36.0);
            int j = MathHelper.floor(y - 2.0 - 30.0);
            if (i >= 0 && j >= 0) {
                Objects.requireNonNull(this.textRenderer);
                int k = Math.min(128 / 9, this.cachedPage.size());
                if (i <= 114) {
                    Objects.requireNonNull(this.client.textRenderer);
                    if (j < 9 * k + k) {
                        Objects.requireNonNull(this.client.textRenderer);
                        int l = j / 9;
                        if (l >= 0 && l < this.cachedPage.size()) {
                            OrderedText orderedText = (OrderedText)this.cachedPage.get(l);
                            return this.client.textRenderer.getTextHandler().getStyleAt(orderedText, i);
                        }

                        return null;
                    }
                }

                return null;
            } else {
                return null;
            }
        }
    }

}

