let transactions = JSON.parse(localStorage.getItem('transactions')) || [];

const balanceElement = document.getElementById('balance');
const transactionHistoryElement = document.getElementById('transaction-history');
const transactionForm = document.getElementById('transaction-form');

function calculateBalance() {
    const balance = transactions.reduce((acc, transaction) => {
        return acc + (transaction.type === 'income' ? transaction.amount : -transaction.amount);
    }, 0);
    balanceElement.textContent = balance.toFixed(2);
}

function renderTransactionHistory() {
    transactionHistoryElement.innerHTML = '';
    transactions.forEach((transaction, index) => {
        const li = document.createElement('li');
        li.textContent = `${transaction.description} - ${transaction.type.charAt(0).toUpperCase() + transaction.type.slice(1)} - ${transaction.amount.toFixed(2)}`;
        transactionHistoryElement.appendChild(li);
    });
}

function saveTransactionsToLocalStorage() {
    localStorage.setItem('transactions', JSON.stringify(transactions));
}

function addTransaction(event) {
    event.preventDefault();
    const description = document.getElementById('description').value.trim();
    const amount = parseFloat(document.getElementById('amount').value.trim());
    const type = document.querySelector('input[name="transaction-type"]:checked').value;

    if (description && !isNaN(amount) && amount !== 0) {
        transactions.push({ description, amount, type });
        saveTransactionsToLocalStorage();
        calculateBalance();
        renderTransactionHistory();
        transactionForm.reset();
    }
}

transactionForm.addEventListener('submit', addTransaction);

calculateBalance();
renderTransactionHistory();