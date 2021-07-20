import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem'

class Dashboard extends Component {
  render() {
    return (
      <div>
        <h1>Welcome to the Dashboard</h1>
        <p>This is the beginning of React.</p>

        <ProjectItem />
      </div>
    )
  }
}

export default Dashboard;