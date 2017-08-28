package demosplitwise.demo.controllers;


import demosplitwise.demo.domain.User;
import demosplitwise.demo.domain.UserGroup;
import demosplitwise.demo.domain.UserTransaction;
import demosplitwise.demo.repositories.UserGroupRepository;
import demosplitwise.demo.repositories.UserRepository;
import demosplitwise.demo.repositories.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import demosplitwise.demo.domain.Transactions;
import demosplitwise.demo.repositories.TransRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransRepository transRepository;

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGroupRepository userGroupRepository;


    @RequestMapping(value = "/transaction/save", method = RequestMethod.POST)
    public void save(@RequestBody Transactions transactions) {
        transRepository.save(transactions);
        List<Long> newList = new ArrayList<>();
        newList.addAll(transactions.lender);
        newList.addAll(transactions.borrower);
        for (long id : transactions.borrower) {
            userTransactionRepository.save(new UserTransaction(transactions.getGroupId(), transactions.getTransID(), id,
                    -1 * (transactions.getAmount() / newList.size())));
            if (transactions.getGroupId() == -1) {
                User userTemp = userRepository.findOne(id);
                userTemp.setDebt(userTemp.getDebt() - (transactions.getAmount() / newList.size()));
                userRepository.save(userTemp);
            } else {
                User userTemp = userRepository.findOne(id);
                UserGroup userGroup = userGroupRepository.findByGroupIdAndUserId(transactions.getGroupId(), id);
                userTemp.setDebt(userTemp.getDebt() - (transactions.getAmount() / newList.size()));
                userGroup.setDebt(userGroup.getDebt() - (transactions.getAmount() / newList.size()));
                userRepository.save(userTemp);
                userGroupRepository.save(userGroup);
            }
        }

        for (long id : transactions.lender) {
            userTransactionRepository.save(new UserTransaction(transactions.getGroupId(), transactions.getTransID(), id,
                    (transactions.getAmount() / transactions.lender.size()) -
                            (transactions.getAmount() / newList.size())));

            if (transactions.getGroupId() == -1) {
                User userTemp = userRepository.findOne(id);
                userTemp.setDebt(userTemp.getDebt() + ((transactions.getAmount() / transactions.lender.size()) -
                        (transactions.getAmount() / newList.size())));
                userRepository.save(userTemp);
            } else {
                User userTemp = userRepository.findOne(id);
                UserGroup userGroup = userGroupRepository.findByGroupIdAndUserId(transactions.getGroupId(), id);
                userTemp.setDebt(userTemp.getDebt() + ((transactions.getAmount() / transactions.lender.size()) -
                        (transactions.getAmount() / newList.size())));
                userGroup.setDebt(userGroup.getDebt() + ((transactions.getAmount() / transactions.lender.size()) -
                        (transactions.getAmount() / newList.size())));
                userRepository.save(userTemp);
                userGroupRepository.save(userGroup);

            }
        }
    }

    @RequestMapping(value = "/transaction/update", method = RequestMethod.PUT)
    public void update(@RequestBody Transactions transactions) {
        transRepository.save(transactions);
        List<Long> newList = new ArrayList<>();
        newList.addAll(transactions.lender);
        newList.addAll(transactions.borrower);
        for (long id : transactions.borrower) {
            UserTransaction userTransaction = userTransactionRepository.findByUserIdAndTransID(id,transactions.getTransID());
            userTransaction.setPartialAmount(-1 * (transactions.getAmount() / newList.size()));
            userTransaction.setUserId(id);
            userTransaction.setTransID(transactions.getTransID());
            userTransaction.setGroupId(transactions.getGroupId());

            userTransactionRepository.save(userTransaction);
            if (transactions.getGroupId() == -1) {
                User userTemp = userRepository.findOne(id);
                userTemp.setDebt(userTemp.getDebt() - (transactions.getAmount() / newList.size()));
                userRepository.save(userTemp);
            } else {
                User userTemp = userRepository.findOne(id);
                UserGroup userGroup = userGroupRepository.findByGroupIdAndUserId(transactions.getGroupId(), id);
                userTemp.setDebt(userTemp.getDebt() - (transactions.getAmount() / newList.size()));
                userGroup.setDebt(userGroup.getDebt() - (transactions.getAmount() / newList.size()));
                userRepository.save(userTemp);
                userGroupRepository.save(userGroup);
            }
        }

        for (long id : transactions.lender) {
            UserTransaction userTransaction = userTransactionRepository.findByUserIdAndTransID(id,transactions.getTransID());
            userTransaction.setPartialAmount((transactions.getAmount() / transactions.lender.size())-
                    (transactions.getAmount() / newList.size()));
            userTransaction.setUserId(id);
            userTransaction.setTransID(transactions.getTransID());
            userTransaction.setGroupId(transactions.getGroupId());

            userTransactionRepository.save(userTransaction);

            if (transactions.getGroupId() == -1) {
                User userTemp = userRepository.findOne(id);
                userTemp.setDebt(userTemp.getDebt() - ((transactions.getAmount() / transactions.lender.size()) -
                        (transactions.getAmount() / newList.size())));
                userRepository.save(userTemp);
            } else {
                User userTemp = userRepository.findOne(id);
                UserGroup userGroup = userGroupRepository.findByGroupIdAndUserId(transactions.getGroupId(), id);
                userTemp.setDebt(userTemp.getDebt() + ((transactions.getAmount() / transactions.lender.size()) -
                        (transactions.getAmount() / newList.size())));
                userGroup.setDebt(userGroup.getDebt() + ((transactions.getAmount() / transactions.lender.size()) -
                        (transactions.getAmount() / newList.size())));
                userRepository.save(userTemp);
                userGroupRepository.save(userGroup);

            }
        }
    }


    @RequestMapping("/transaction/findAll")
    public List<Transactions> findAll() {
        List<Transactions> mylist = new ArrayList();
        for (Transactions trans : transRepository.findAll()) {
            trans.setLender(allLenders(trans.getTransID()));
            trans.setBorrower(allBorrowers(trans.getTransID()));
            mylist.add(trans);
        }

        return mylist;
    }

    @RequestMapping("/transaction/findById")
    public Transactions findById(@RequestParam("transId") long id) {
        Transactions transaction = transRepository.findOne(id);
        transaction.setLender(allLenders(id));
        transaction.setBorrower(allBorrowers(id));
        return transaction;
    }

    @RequestMapping("/transaction/findAllByGroupId")
    public List<Transactions> findAllByGroupId(@RequestParam("groupId") long id) {
        List<Transactions> mylist = new ArrayList();
        for (Transactions transactions : transRepository.findByGroupId(id)) {
            transactions.setLender(allLenders(transactions.getTransID()));
            transactions.setBorrower(allBorrowers(transactions.getTransID()));
            mylist.add(transactions);
        }

        return mylist;
    }

    @RequestMapping("/transaction/findAllByUserId")
    public List<Transactions> findAllByUserId(@RequestParam("userId") long id) {
        List<Transactions> mylist = new ArrayList();
        for (UserTransaction userTransaction : userTransactionRepository.findByUserId(id)) {
            Transactions transaction = transRepository.findOne(userTransaction.getTransID());
            transaction.setLender(allLenders(transaction.getTransID()));
            transaction.setBorrower(allBorrowers(transaction.getTransID()));
            mylist.add(transaction);
        }
        return mylist;
    }

    @RequestMapping("/transaction/findGroupByUserId")
    public List<Transactions> findGroupByUserId(@RequestParam("groupId") long groupId,
                                                @RequestParam("userId") long userId) {
        List<Transactions> mylist = new ArrayList();
        for (UserTransaction userTransaction : userTransactionRepository.findByGroupIdAndUserId(groupId,userId)) {
            Transactions transaction = transRepository.findOne(userTransaction.getTransID());
            transaction.setLender(allLenders(transaction.getTransID()));
            transaction.setBorrower(allBorrowers(transaction.getTransID()));
            mylist.add(transaction);
        }
        return mylist;
    }

    class UserDebt{
        private long userId;
        private String userName;
        private double debt;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public double getDebt() {
            return debt;
        }

        public void setDebt(double debt) {
            this.debt = debt;
        }
    }

    @RequestMapping("/transaction/findAmountByTransId")
    public List<UserDebt> findAmountByTransId(@RequestParam("transId")long transId){
        List<UserDebt> mylist = new ArrayList<>();
        for(UserTransaction userTransaction : userTransactionRepository.findByTransID(transId)){
            UserDebt userDebt  = new UserDebt();
            userDebt.setUserId(userTransaction.getUserId());
            userDebt.setUserName(userRepository.findOne(userTransaction.getUserId()).getName());
            userDebt.setDebt(userTransaction.getPartialAmount());
            mylist.add(userDebt);
        }
        return mylist;
    }
    @RequestMapping("/transaction/findNonGroupByUserId")
    public List<Transactions> findNonGroupByUserId(@RequestParam("userId") long userId) {
        List<Transactions> mylist = new ArrayList();
        for (UserTransaction userTransaction : userTransactionRepository.findByGroupIdAndUserId(-1,userId)) {
            Transactions transaction = transRepository.findOne(userTransaction.getTransID());
            transaction.setLender(allLenders(transaction.getTransID()));
            transaction.setBorrower(allBorrowers(transaction.getTransID()));
            mylist.add(transaction);
        }
        return mylist;
    }

    public List<Long> allLenders(long transid){
        List<Long> lenders = new ArrayList<Long>();
        List<UserTransaction> userTransactions = userTransactionRepository.findByTransID(transid);
        for (UserTransaction userTransaction : userTransactions) {
            if(userTransaction.getPartialAmount()>0)
                lenders.add(userTransaction.getUserId());
        }
        return lenders;
    }

    public List<Long> allBorrowers (long transid){
        List<Long> borrowers = new ArrayList<Long>();
        List<UserTransaction> userTransactions = userTransactionRepository.findByTransID(transid);
        for (UserTransaction userTransaction : userTransactions) {
            if(userTransaction.getPartialAmount()<0)
                borrowers.add(userTransaction.getUserId());
        }
        return borrowers;
    }
}

