package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.ISelectedItem;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SelectedItem implements ISelectedItem {
   private MenuRow menuRow;
   private int count;

   @Override
   public IMenuRow getRow() {
      return this.menuRow;
   }

   @Override
   public int getCount() {
      return this.count;
   }
}
