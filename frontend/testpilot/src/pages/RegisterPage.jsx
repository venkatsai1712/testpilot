import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'

function RegisterPage({ onLogin }) {
  const [message, setMessage] = useState('')
  const navigate = useNavigate()

  async function handleSubmit(event) {
    event.preventDefault()

    const formData = new FormData(event.currentTarget)
    const name = formData.get('name').trim()
    const email = formData.get('email').trim()
    const password = formData.get('password')

    if (!name) {
      setMessage('Name is required.')
      return
    }

    if (!email.toLowerCase().endsWith('@gmail.com')) {
      setMessage('Please use a valid @gmail.com email address.')
      return
    }

    try {
      const response = await fetch('/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', Accept: 'application/json' },
        body: JSON.stringify({ name, email, password }),
      })
      const data = await response.json()

      if (!response.ok) {
        setMessage(data.details || data.message || 'Registration failed.')
        return
      }

      onLogin()
      navigate('/')
    } catch (error) {
      setMessage(error.message)
    }
  }

  return (
    <main className="auth-page">
      <section className="auth-card">
        <p className="brand">TestPilot</p>
        <h1>Create your account</h1>
        <p className="subtitle">Sign up to get started.</p>

        <form onSubmit={handleSubmit}>
          <label htmlFor="name">Name</label>
          <input id="name" name="name" type="text" maxLength="250" required />

          <label htmlFor="email">Email</label>
          <input id="email" name="email" type="email" required />

          <label htmlFor="password">Password</label>
          <input id="password" name="password" type="password" minLength="8" maxLength="16" required />

          <button type="submit">Create account</button>
        </form>

        {message && <p className="form-message">{message}</p>}

        <p className="switch-page">
          Already have an account? <Link to="/login">Log in</Link>
        </p>
      </section>
    </main>
  )
}

export default RegisterPage
