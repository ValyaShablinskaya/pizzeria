package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.ISelectedItem;
import lombok.Builder;

@Builder
public class SelectedItem implements ISelectedItem {
   private final MenuRow menuRow;
   private final int count;

   @Override
   public IMenuRow getRow() {
      return this.menuRow;
   }

   @Override
   public int getCount() {
      return this.count;
   }
}
