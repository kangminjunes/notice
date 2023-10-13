package com.gdu.myapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdu.myapp.dao.ContactDao;
import com.gdu.myapp.dto.ContactDto;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactDao contactDao;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public List<ContactDto> getContactList() {
        try {
            return contactDao.selectList();
        } catch (Exception e) {
            // Log the exception or rethrow as a custom exception
            e.printStackTrace();
            throw new RuntimeException("리스트오류");
        }
    }

    @Override
    public int addContact(ContactDto contactDto) {
        try {
            return contactDao.insert(contactDto);
        } catch (Exception e) {
            // Log the exception or rethrow as a custom exception
            e.printStackTrace();
            throw new RuntimeException("데이터추가안됨");
        }
    }

    @Override
    public int modifyContact(ContactDto contactDto) {
        try {
            return contactDao.update(contactDto);
        } catch (Exception e) {
            // Log the exception or rethrow as a custom exception
            e.printStackTrace();
            throw new RuntimeException("변경안됬음");
        }
    }

    @Override
    public int deleteContact(int notice_no) {
        try {
            return contactDao.delete(notice_no);
        } catch (Exception e) {
            // Log the exception or rethrow as a custom exception
            e.printStackTrace();
            throw new RuntimeException("삭제실패");
        }
    }

    @Override
    public ContactDto getContactByNo(int notice_no) {
        try {
            return contactDao.selectContactByNo(notice_no);
        } catch (Exception e) {
            // Log the exception or rethrow as a custom exception
            e.printStackTrace();
            throw new RuntimeException("연결실패");
        }
    }
}
