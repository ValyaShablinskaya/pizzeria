//package by.it_academy.jd2.mk_jd2_92_22.services;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IDoneOrderMapper;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuService;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class MenuServiceTest {
//    @Mock
//    private MenuDao menuDao;
//    @Mock
//    private IMenuMapper iMenuMapper;
//    @InjectMocks
//    private MenuService menuService;
//
//    @Test
//    void addMenuShouldReturnCorrectResult() {
//        MenuDTO menuDTO = MenuDTO.builder()
//                .name("Autumn")
//                .enabled(true)
//                .build();
//        Menu menu= Menu.builder()
//                .id(1L)
//                .name("Autumn")
//                .enabled(true)
//                .build();
//
////        try (MockedStatic<IMenuMapper> theMock = Mockito.mockStatic(IMenuMapper.class)) {
////            theMock.when(() -> IMenuMapper.INSTANCE.convertToMenu(menuDTO)).thenReturn(menu);
////            theMock.when(() -> IMenuMapper.INSTANCE.convertToMenuDTO(menu)).thenReturn(menuDTO);
////        }
//
//        when(iMenuMapper.convertToMenu(menuDTO)).thenReturn(menu);
//        when(menuDao.save(menu)).thenReturn(Optional.of(menu));
//
//        when(iMenuMapper.convertToMenuDTO(menu)).thenReturn(menuDTO);
//
//        assertEquals(menuDTO, menuService.add(menuDTO));
//
//        verify(IMenuMapper.INSTANCE).convertToMenu(menuDTO);
//        verify(menuDao).save(menu);
//        verify(IMenuMapper.INSTANCE).convertToMenuDTO(menu);
//
//    }
//
//////    @Test
//////    void findMenuByIdShouldReturnCorrectResult() {
//////        Menu menu= Menu.builder()
//////                .id(1L)
//////                .name("Autumn")
//////                .enabled(true)
//////                .build();
//////
//////        when(menuDao.findById(menu.getId())).thenReturn(Optional.of(menu));
//////        menuService.findById(menu.getId());
//////        verify(menuDao).findById(menu.getId());
//////    }
//////
//////    @Test
//////    void findMenuByIdShouldReturnExceptionWhenGetIncorrectParameters() {
//////        Menu menu= Menu.builder()
//////                .id(1L)
//////                .name("Autumn")
//////                .enabled(true)
//////                .build();
//////
//////        when(menuDao.findById(menu.getId())).thenReturn(Optional.empty());
//////        assertThrows(EntityNotFoundException.class, () -> menuService.findById(menu.getId()));
//////        verifyNoMoreInteractions(menuDao);
//////    }
//////
//////    @Test
//////    void findAllMenusShouldReturnCorrectResult() {
//////        List<Menu> menus = new ArrayList<>();
//////
//////        menus.add(Menu.builder()
//////                .id(1L)
//////                .name("Autumn")
//////                .enabled(true)
//////                .build());
//////
//////        menus.add(Menu.builder()
//////                .id(2L)
//////                .name("Weekend")
//////                .enabled(true)
//////                .build());
//////
//////        when(menuDao.findAll()).thenReturn(menus);
//////        menuService.findAll();
//////        verify(menuDao).findAll();
//////    }
//////
//////    @Test
//////    void updateMenuShouldReturnCorrectResult() {
//////        LocalDateTime updateData =
//////                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//////
//////        Menu menu = Menu.builder()
//////                .id(1L)
//////                .name("Autumn")
//////                .enabled(true)
//////                .updateDate(updateData)
//////                .build();
//////
//////        when(menuDao.findById(menu.getId())).thenReturn(Optional.of(menu));
//////        when(menuDao.update(menu)).thenReturn(Optional.of(menu));
//////        menuService.update(menu, menu.getId(), updateData);
//////        verify(menuDao).update(menu);
//////    }
//////
//////    @Test
//////    void removeMenuByIdShouldReturnCorrectResult() {
//////        LocalDateTime updateData =
//////                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//////
//////        Menu menu = Menu.builder()
//////                .id(1L)
//////                .name("Autumn")
//////                .enabled(true)
//////                .updateDate(updateData)
//////                .build();
//////
//////        when(menuDao.findById(menu.getId())).thenReturn(Optional.of(menu));
//////        menuService.deleteById(menu.getId(), updateData);
//////        verify(menuDao).deleteById(menu.getId());
//////    }
//}
