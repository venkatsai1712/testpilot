import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'

function LoginPage({ onLogin }) {
  const [message, setMessage] = useState('')
  const navigate = useNavigate()

  async function handleSubmit(event) {
    event.preventDefault()

    const formData = new FormData(event.currentTarget)
    const email = formData.get('email').trim()
    const password = formData.get('password')

    if (!email.toLowerCase().endsWith('@gmail.com')) {
      setMessage('Please use a valid @gmail.com email address.')
      return
    }

    try {
      const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', Accept: 'application/json' },
        body: JSON.stringify({ email, password }),
      })
      const data = await response.json()

      if (!response.ok) {
        setMessage(data.details || data.message || 'Login failed.')
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
        <h1>Welcome back</h1>
        <p className="subtitle">Log in to continue to your account.</p>

        <form onSubmit={handleSubmit}>
          <label htmlFor="email">Email</label>
          <input id="email" name="email" type="email" required />

          <label htmlFor="password">Password</label>
          <input id="password" name="password" type="password" minLength="8" required />

          <button type="submit">Log in</button>
        </form>

        {message && <p className="form-message">{message}</p>}

        <p className="switch-page">
          Don&apos;t have an account? <Link to="/register">Create one</Link>
        </p>
      </section>
    </main>
  )
}

export default LoginPage
