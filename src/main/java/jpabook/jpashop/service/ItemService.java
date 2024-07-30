package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//이러한 단순한 위임만 하는 경우에는 서비스 없이 컨트롤러에서 바로 리포지토리 접근해도 괜찮다고 봄
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //서비스 레벨의 트랜잭션이 리드온리이기 때문에 저장이 안되어 이것은 @Transactional이 필요하다.
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        //id를 기반으로 영속성 아이템을 찾아옴
        Item findItem = itemRepository.findOne(itemId);
        //setter 보다는 엔티티 안에서 바로 추적할 수 있는 메서드를 만들어라
        //findItem.change(name, price, stockQuantity); //이런 식으로
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
