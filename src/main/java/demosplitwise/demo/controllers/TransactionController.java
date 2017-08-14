package demosplitwise.demo.controllers;

import demosplitwise.demo.domain.UserGroup;
import demosplitwise.demo.domain.UserTransaction;
import demosplitwise.demo.repositories.UserGroupRepository;
import demosplitwise.demo.repositories.UserTransactionRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import demosplitwise.demo.domain.Transactions;
import demosplitwise.demo.repositories.TransRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransRepository transRepository;

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    UserGroupRepository userGroupRepository;


    @RequestMapping(value = "/transaction/save", method = RequestMethod.POST)
    public void save(@RequestBody Transactions transactions) {
        transRepository.save(transactions);
        List<Long> newList = new ArrayList<>();
        newList.addAll(transactions.lender);
        newList.addAll(transactions.borrower);
        for (long id : transactions.borrower) {
            userTransactionRepository.save(new UserTransaction(transactions.getTransID(), id,
                    -1 * (transactions.getAmount() / newList.size())));
        }

        for (long id : transactions.lender) {
            userTransactionRepository.save(new UserTransaction(transactions.getTransID(), id,
                    (transactions.getAmount() / transactions.lender.size()) -
                            (transactions.getAmount() / newList.size())));
        }
    }

    @RequestMapping(value = "/transaction/update", method = RequestMethod.PUT)
    public void update(@RequestBody Transactions transactions) {
        transRepository.save(transactions);
        List<Long> newList = new ArrayList<>();
        newList.addAll(transactions.lender);
        newList.addAll(transactions.borrower);
        for (long id : transactions.borrower) {
            userTransactionRepository.save(new UserTransaction(transactions.getTransID(), id,
                    -1 * (transactions.getAmount() / newList.size())));
        }

        for (long id : transactions.lender) {
            userTransactionRepository.save(new UserTransaction(transactions.getTransID(), id,
                    (transactions.getAmount() / transactions.lender.size()) -
                            (transactions.getAmount() / newList.size())));
        }
    }


    @RequestMapping("/transaction/findAll")
    public List<Transactions> findAll() {
        List<Transactions> mylist = new ArrayList();
        System.out.println("fffffffff");
        for (Transactions trans : transRepository.findAll()) {
            mylist.add(trans);
        }

        return mylist;
    }

    @RequestMapping("/transaction/findById")
    public Transactions findById(@RequestParam("id") long id) {
        return transRepository.findOne(id);
    }

    @RequestMapping("/transaction/findAllByGroupId")
    public List<Transactions> findAllByGroupId(@RequestParam("id") long id) {
        List<Transactions> mylist = new ArrayList();
        for (Transactions transactions : transRepository.findByGroupId(id)) {
            mylist.add(transactions);
        }

        return mylist;
    }

    @RequestMapping("/transaction/findAllByUserId")
    public List<Transactions> findAllByUserId(@RequestParam("id") long id) {
        List<Transactions> mylist = new ArrayList();
        for (UserTransaction userTransaction : userTransactionRepository.findByUserId(id)) {
            mylist.add(transRepository.findOne(userTransaction.getTransID()));
        }
        return mylist;
    }

    @RequestMapping("/transaction/findGroupByUserId")
    public List<Transactions> findGroupByUserId(@RequestParam("groupId") long groupId,
                                                @RequestParam("userId") long userId) {
        List<Transactions> mylist = new ArrayList();
        for (UserGroup userGroup : userGroupRepository.findByGroupIdAndUserId(groupId, userId)) {
            mylist.add(transRepository.findOne(userGroup.getTransId()));
        }
        return mylist;
    }

    @RequestMapping("/transaction/findNonGroupByUserId")
    public List<Transactions> findNonGroupByUserId(@RequestParam("userId") long userId) {
        List<Transactions> mylist = new ArrayList();
        for (UserGroup userGroup : userGroupRepository.findByGroupIdAndUserId(-1, userId)) {
            mylist.add(transRepository.findOne(userGroup.getTransId()));
        }
        return mylist;
    }
}

