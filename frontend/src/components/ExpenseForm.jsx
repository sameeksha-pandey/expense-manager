import { useState } from 'react';
import api from '../services/api';

function ExpenseForm({ onExpenseAdded }) {

    const [formData, setFormData] = useState({
        date: '',
        amount: '',
        vendorName: '',
        description: ''
    });

    const [message, setMessage] = useState('');
    const [error, setError] = useState('');
    const [submitting, setSubmitting] = useState(false);

    const handleChange = (event) => {

        const { name, value } = event.target;

        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (event) => {

        event.preventDefault();

        setMessage('');
        setError('');
        setSubmitting(true);

        try {

            const requestData = {
                date: formData.date,
                amount: Number(formData.amount),
                vendorName: formData.vendorName,
                description: formData.description
            };

            const response = await api.post(
                '/expenses',
                requestData
            );

            setMessage(
                `Expense added successfully. Category: ${response.data.category}`
            );

            setFormData({
                date: '',
                amount: '',
                vendorName: '',
                description: ''
            });

            if (onExpenseAdded) {
                onExpenseAdded();
            }

        } catch (error) {

            console.error('Failed to add expense:', error);

            setError(
                'Failed to add expense. Please check your details.'
            );

        } finally {

            setSubmitting(false);
        }
    };

    return (
        <div className="dashboard-section">

            <h2>Add Expense</h2>

            <form
                className="expense-form"
                onSubmit={handleSubmit}
            >

                <div className="form-group">
                    <label htmlFor="date">Date</label>

                    <input
                        id="date"
                        type="date"
                        name="date"
                        value={formData.date}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="amount">Amount</label>

                    <input
                        id="amount"
                        type="number"
                        name="amount"
                        value={formData.amount}
                        onChange={handleChange}
                        placeholder="Enter amount"
                        min="0"
                        step="0.01"
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="vendorName">
                        Vendor Name
                    </label>

                    <input
                        id="vendorName"
                        type="text"
                        name="vendorName"
                        value={formData.vendorName}
                        onChange={handleChange}
                        placeholder="e.g. Swiggy"
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="description">
                        Description
                    </label>

                    <input
                        id="description"
                        type="text"
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                        placeholder="Enter description"
                    />
                </div>

                <div className="form-actions">

                    <button
                        className="primary-button"
                        type="submit"
                        disabled={submitting}
                    >
                        {submitting
                            ? 'Adding...'
                            : 'Add Expense'}
                    </button>

                </div>

            </form>

            {message && (
                <p className="success-message">
                    {message}
                </p>
            )}

            {error && (
                <p className="error-message">
                    {error}
                </p>
            )}

        </div>
    );
}

export default ExpenseForm;